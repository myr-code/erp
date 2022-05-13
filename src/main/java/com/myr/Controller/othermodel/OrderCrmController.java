package com.myr.Controller.othermodel;

import com.myr.Bean.Icstockbill;
import com.myr.Bean.Item;
import com.myr.Bean.MrpPurReq;
import com.myr.Bean.Supplier;
import com.myr.Bean.othermodel.CustomerCrm;
import com.myr.Bean.othermodel.OrderCrm;
import com.myr.Bean.othermodel.Urlcontent;
import com.myr.Service.PurReqService;
import com.myr.Service.othermodel.OrderCrmService;
import com.myr.utils.FileUploadUtils;
import com.myr.utils.GetParValues;
import com.myr.utils.MessageRequest;
import com.myr.utils.PageUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller
@Scope("prototype")
public class OrderCrmController {
    @Resource
    PurReqService purReqService;

    @Resource
    OrderCrmService orderCrmService;

    //01-添加
    @RequestMapping("/OrderCrm_add")
    @ResponseBody
    public void OrderCrm_add(@RequestParam("itemImg") MultipartFile[] itemImg, HttpServletRequest request, HttpServletResponse response) throws IOException {

        for (int i = 0; i < itemImg.length; i++) {
            MultipartFile file = itemImg[i];
            if(file.getSize() > 1024*1024*10){//单个文件不能大于10M
                response.getWriter().write("<script>alert('单个文件不能大于10M!');location.href='/erp/crm/page_OrderCrmAdd';</script>");
                break;
            }
        }

        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty0");//获取item后面的num
            List<OrderCrm> orderCrms = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            String custName = request.getParameter("custName");//客户名称
            String billDate = request.getParameter("billDate");
            String sendAddress = request.getParameter("sendAddress");
            String collAddress = request.getParameter("collAddress");
            String remark = request.getParameter("remark");
            String billNo = orderCrmService.getBillNo(billDate);
            Integer billStaf = Integer.parseInt(request.getParameter("billStaf"));

            //产品图片
            List<String> imgNames = FileUploadUtils.FilesUpload(itemImg);
            String imgs = imgNames.toString().replace(" ", "").replace("[", "").replace("]", "");


            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                OrderCrm orderCrm = new OrderCrm();

                String item = request.getParameter("item0" + num);//item
                //*String itemName = request.getParameter("itemImg0" + num);//产品图片
                double oneWeight = Double.parseDouble(request.getParameter("oneWeight0" + num));
                String oneSize = request.getParameter("oneSize0" + num);//item model
                double oneVolume = Double.parseDouble(request.getParameter("oneVolume0" + num));
                double qty = Double.parseDouble(request.getParameter("qty0" + num));
                double weightSum = Double.parseDouble(request.getParameter("weightSum0" + num));
                double volumeSum = Double.parseDouble(request.getParameter("volumeSum0" + num));
                String purpose = request.getParameter("purpose0" + num);//完成日期
                String bjhd = request.getParameter("bjhd0" + num);//完成日期
                String rowRemark = request.getParameter("rowRemark" + num);//行备注

                orderCrm.setBillNo(billNo);
                orderCrm.setBillDate(billDate);
                orderCrm.setCustName(custName);
                orderCrm.setEntryId(entry);
                orderCrm.setSendAddress(sendAddress);
                orderCrm.setCollAddress(collAddress);
                orderCrm.setItem(item);
                orderCrm.setItemImg(imgs);
                orderCrm.setOneWeight(oneWeight);
                orderCrm.setOneSize(oneSize);
                orderCrm.setOneVolume(oneVolume);
                orderCrm.setQty(qty);
                orderCrm.setWeightSum(weightSum);
                orderCrm.setVolumeSum(volumeSum);
                orderCrm.setPurpose(purpose);
                orderCrm.setBjhd(bjhd);
                orderCrm.setRemark(remark);
                orderCrm.setRowRemark(rowRemark);
                orderCrm.setBillStaf(billStaf);


                orderCrms.add(orderCrm);
                System.out.println("item="+orderCrm);
                entry++;//分录号自增
            }


            Integer count = orderCrmService.add_OrderCrm(orderCrms);

            if(count > 0){
                //成功
                response.getWriter().write("<script>alert('新增成功!');location.href='/erp/crm/page_OrderCrmAdd';</script>");
            }else {
                //失败
                response.getWriter().write("<script>alert('新增失败!');location.href='/erp/crm/page_OrderCrmAdd';</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('新增失败!');location.href='/erp/crm/page_OrderCrmAdd';</script>");
        }
    }

    //序时簿
    @RequestMapping("/OrderCrmIndex")
    public String OrderCrmIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                                   @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize, @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("startpage", (startpage - 1) * pagesize);
        map.put("pagesize", pagesize);
        map.put("cnm",AllQuery);

        //获取总条数
        int countTatol = orderCrmService.getCounts(map);
        //主数据
        List<OrderCrm> orderCrms = orderCrmService.OrderCrm_page(map);
        System.out.println(orderCrms);
        //封装数据
        PageUtils<OrderCrm> pageUtils = new PageUtils<OrderCrm>(startpage, pagesize, countTatol, orderCrms);
        model.addAttribute("datas",pageUtils);
        model.addAttribute("AllQuery",AllQuery);

        return "crm/OrderCrmIndex";
    }

    //去到编辑页面
    @RequestMapping("/OrderCrmEdit/{fid}")
    public String OrderCrmEdit(@PathVariable("fid") int fid, Model model, HttpServletRequest request) {
        List<MrpPurReq> purReqById = purReqService.getPurReqById(fid);
        MrpPurReq mrpPurReq = null;
        if(purReqById.size()>0&&purReqById!=null){
            mrpPurReq = purReqById.get(0);
        }
        System.out.println(mrpPurReq);
        model.addAttribute("data",mrpPurReq);
        model.addAttribute("datas",purReqById);
        return "/mrp/edit/PurReqEdit";
    }

    //更新
    @RequestMapping("/OrderCrm_update")
    @ResponseBody
    public MessageRequest OrderCrm_update(HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "qty");//获取item后面的num
            List<MrpPurReq> mrpPurReqs = new ArrayList<>();//item集合

            //添加头 客户、日期、备注、业务员
            /*Integer custId = Integer.parseInt(request.getParameter("custId"));
            Customer customer = new Customer();
            customer.setFid(custId);*/
            Integer suppId = Integer.parseInt(request.getParameter("suppId"));
            Supplier supplier = new Supplier();
            supplier.setFid(suppId);
            String billDate = request.getParameter("billDate");
            String remark = request.getParameter("remark");
            String billNo = request.getParameter("billNo");
            String mrpNo = request.getParameter("mrpNo");
            Integer depStaffId = Integer.parseInt(request.getParameter("depStaffId"));

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                MrpPurReq mrpPurReq = new MrpPurReq();

                Integer itemId = Integer.parseInt(request.getParameter("itemId" + num));//item ID
                /*String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位*/
                String custOrderNum = request.getParameter("custOrderNum" + num);//客户订单号
                double qty = Double.parseDouble(request.getParameter("qty" + num));//数量
                /*Integer stockId = Integer.parseInt(request.getParameter("stockId" + num));//默认仓库*/
                String batchNumber = request.getParameter("batchNumber" + num);//批号
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                /*double taxPriceNo = Double.parseDouble(request.getParameter("taxPriceNo" + num));//不含税单价*/
                String rowRemark = request.getParameter("rowRemark" + num);//行备注
                String finishDate = request.getParameter("finishDate" + num);//完成日期
                int sourFid = Integer.parseInt(request.getParameter("sourFid" + num));//源单内码
                String sourBillNo = request.getParameter("sourBillNo" + num);//源单单号
                int sourEntryId = Integer.parseInt(request.getParameter("sourEntryId" + num));//源单分录号
                String sourType = request.getParameter("sourType" + num);//源单类型

                mrpPurReq.setBillNo(billNo);
                mrpPurReq.setBillDate(billDate);
                mrpPurReq.setSuppId(supplier);
                mrpPurReq.setEntryId(entry);
                mrpPurReq.setMrpNo(mrpNo);
                Item item = new Item();
                item.setFid(itemId);
                mrpPurReq.setItemId(item);
                mrpPurReq.setCustOrderNum(custOrderNum);
                mrpPurReq.setFinishDate(("").equals(finishDate)||finishDate == null ? null:finishDate);
                mrpPurReq.setQty(qty);
                mrpPurReq.setBatchNumber(batchNumber);
                mrpPurReq.setTaxPrice(taxPrice);
                mrpPurReq.setTaxPriceNo(taxPrice);
                mrpPurReq.setFcess(0);
                mrpPurReq.setRemark(remark);
                mrpPurReq.setRowRemark(rowRemark);
                mrpPurReq.setSourEntryId(sourEntryId);
                mrpPurReq.setSourType(sourType);
                mrpPurReq.setBillStaf(depStaffId);

                mrpPurReqs.add(mrpPurReq);
                System.out.println("item="+mrpPurReq);
                entry++;//分录号自增
            }

            /*//删除计划单
            purReqService.delPurReq(billNo);
            //添加计划单
            Integer count = purReqService.add_PurReq(mrpPurReqs);*/

            MrpPurReq mrpPurReq = new MrpPurReq();
            mrpPurReq.setBillNo(billNo);
            Integer count = purReqService.PurReq_update(mrpPurReq,mrpPurReqs);

            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"更新成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"更新失败",null);
            }
        } catch (Exception e) {
            msg = new MessageRequest(500,"更新失败",null);
        }
        return msg;
    }

    //删除
    @RequestMapping("/OrderCrm_del")
    @ResponseBody
    public MessageRequest OrderCrm_del(HttpServletRequest request) {
        Set<String> bill_list = new HashSet<>();//被引用单据列表
        String[] datas = request.getParameterValues("datas[]");//前端数组获取
        Integer billnum = 0;//总单据数量
        Integer quoted = 0;//单据被引用数量
        Integer succ = 0;//成功删除单据数量
        Integer isdel = 0;//单据不存在的数量
        MessageRequest msg = null;

        try {
            for (String data : datas) {
                List<MrpPurReq> purReqById = purReqService.getPurReqById(Integer.parseInt(data));
                if(purReqById.size()>0&&purReqById!=null){
                    String billNo = purReqById.get(0).getBillNo();
                    Integer count = purReqService.delPurReq(billNo);
                    if(count>0){//删除成功
                        succ++;
                    }
                    if(count==0){//已被删除或不存在
                        isdel++;
                    }
                    if(count<0){//删除失败 单据被引用
                        //共选择xx张单据，成功删除XX张，XX张单据被引用不可删除
                        Set<String> strings = purReqService.PurReq_isQuoted(billNo);
                        bill_list.addAll(strings);
                        quoted++;
                    }
                    billnum++;//处理完成一张单据
                }
            }

            if(succ == billnum){
                //删除成功单据的数量=总单据数量
                msg = new MessageRequest(200,"全部删除成功!",bill_list);
            }else if(quoted>0){
                //部分删除成功
                msg = new MessageRequest(250,"部分删除成功,其余已被引用，不能删除!",bill_list);
            }else if(isdel==billnum){
                //已被删除或不存在
                msg = new MessageRequest(251,"单据已被删除或系统不存在选择的单据!",null);
            }else{
                msg = new MessageRequest(500,"删除失败!",bill_list);
            }

        } catch (Exception e) {
            //登录失败
            msg = new MessageRequest(500,"删除失败",bill_list);
        }
        return msg;
    }

    @RequestMapping("/custCrm_all")
    @ResponseBody
    public List<CustomerCrm> custCrm_all(){
        List<CustomerCrm> customerCrms = orderCrmService.CustCrm_All();
        return customerCrms;
    }

    /*
    * 1、收集 客户详情 链接
    * 2、
    * */
    @RequestMapping("/add_urlContent")
    @ResponseBody
    public MessageRequest add_urlContent(String Url) throws IOException {
        System.out.println(Url);
        //创建URL对象  参数设置需要爬取的网址 也就是我们方法传过来的参数
        URL url = new URL(Url);

        //得到一个HttpURLConnection 对象
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        //防止出现Server returned HTTP response code: 403 for URL 的错误
        //也就是服务器的安全设置不接受Java程序作为客户端访问  所以我们进行安全设置
        huc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //通过HttpURLConnection获得输入流对象
        InputStream is = huc.getInputStream();

        //使用缓冲字符输入流获取源码  设置编码
        BufferedReader r = new BufferedReader(new InputStreamReader(is,"utf-8"));
        String line;

        //写入文件中
        //BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\1.txt"));
        //readLine()一次读一行
        Integer i = 1;
        List<Urlcontent> urlcontents = new ArrayList<>();
        int flag = 0;//是否找到body头 1是 0否
        while ((line = r.readLine()) != null){
            //排查空行
            if(line.length()<=0){
                continue;
            }
            //只要body里数据
            if(line.contains("<body")){
                flag = 1;
            }

            if(flag == 0){
                continue;
            }

            Urlcontent urlcontent = new Urlcontent();
            urlcontent.setUrl(Url);
            urlcontent.setRows(i);
            urlcontent.setRowContent(line);
            urlcontents.add(urlcontent);

            //包含</body> 则结束
            if (line.contains("</body")){
                break;
            }
            /*bw.write(line);
            //读完一行就换行
            bw.newLine();
            //清空缓冲区
            bw.flush();*/
            i++;
        }
        orderCrmService.add_UrlContent(urlcontents);
        //关闭资源
        //bw.close();
        r.close();
        is.close();
        return new MessageRequest(188,"添加成功!",null);
    }

    /**
     * 点击crm订单的图片 返回图片清单
     * @return
     */
    @RequestMapping("/ShowFileList_CRM/{fid}")
    @ResponseBody
    public List<String> ShowFileList_CRM(@PathVariable("fid") Integer fid){
        String s = orderCrmService.getfileNameById(fid);
        ArrayList<String> filenames = new ArrayList<>();
        filenames.add(s);
        return filenames;
    }

    /**
     * 文件下载 在线
     * @return
     */
    @RequestMapping("/fileOnline/{filename}")
    @ResponseBody
    public void fileOnline(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        String url = "C:/public/upload/"+filename;
        FileUploadUtils.fileDownLoad_OnLine(url,response,true);
    }


    /**
     * 单文件下载
     * @return
     */
    @RequestMapping("/fileDownLoadByOne/{filename}")
    @ResponseBody
    public void fileDownLoad(
            @PathVariable("filename") String filename,
            HttpServletResponse response, HttpServletRequest request) throws Exception {
        String url = "C:/public/upload/"+filename;
        FileUploadUtils.fileDownLoad(url,response);
    }

}