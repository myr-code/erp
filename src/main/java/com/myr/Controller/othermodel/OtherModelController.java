package com.myr.Controller.othermodel;

import com.myr.Bean.Bom;
import com.myr.Bean.Bomentry;
import com.myr.Bean.othermodel.OtherModel;
import com.myr.Bean.othermodel.OtherModelEntry;
import com.myr.Service.BomEntryService;
import com.myr.Service.BomService;
import com.myr.Service.othermodel.OtherModelService;
import com.myr.utils.DateOption;
import com.myr.utils.GetParValues;
import com.myr.utils.MessageRequest;
import com.myr.utils.PageUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("prototype")
public class OtherModelController {
    @Resource
    OtherModelService otherModelService;

    /*//01-添加
    @RequestMapping("/BOM_add")
    @ResponseBody
    public MessageRequest BOM_add(Bom bom, HttpServletRequest request) {
        MessageRequest msg = null;
        try {
            List<String> nums = GetParValues.GetParValuesNum(request, "rowRemark");//获取item后面的num
            List<Bomentry> bomentries = new ArrayList<>();//item集合

            //整理item集合
            int entry = 1;
            for (String num : nums) {//后面的序号  1 2 3
                Bomentry bomentry = new Bomentry();

                Integer cuid = Integer.parseInt(request.getParameter("cuid" + num));//item ID
                String itemName = request.getParameter("itemName" + num);//item name
                String itemCode = request.getParameter("itemCode" + num);//item name
                String itemModel = request.getParameter("itemModel" + num);//item model
                String custItemCode = request.getParameter("custItemCode" + num);//custitem code
                String custItemModel = request.getParameter("custItemModel" + num);//custitem model
                String unitName = request.getParameter("unitName" + num);//单位
                Integer qty = Integer.parseInt(request.getParameter("qty" + num));//数量
                double taxPrice = Double.parseDouble(request.getParameter("taxPrice" + num));//含税单价
                Integer defSuppId = Integer.parseInt(request.getParameter("defSuppId" + num));//完成时间
                String defaultSupplierIdName = request.getParameter("defaultSupplierIdName" + num);//完成时间
                String rowRemark = request.getParameter("rowRemark" + num);//完成时间

                bomentry.setEntryId(entry);
                bomentry.setMuid(bom.getMuid());
                bomentry.setMqty(bom.getQty());
                bomentry.setCuid(cuid);
                bomentry.setItemCode(itemCode);
                bomentry.setItemName(itemName);
                bomentry.setItemModel(itemModel);
                bomentry.setCustItemCode(custItemCode);
                bomentry.setCustItemModel(custItemModel);
                bomentry.setQty(qty);
                bomentry.setTaxPrice(taxPrice);
                bomentry.setUnitName(unitName);
                bomentry.setDefSuppId(defSuppId);
                bomentry.setDefSuppName(defaultSupplierIdName);
                bomentry.setRowRemark(rowRemark);


                bomentries.add(bomentry);
                System.out.println("item="+bomentry);
                entry++;
            }
            System.out.println("要保存的订单头="+bom);

            //添加头和体
            bom.setBillNo(bomService.getBillNo(bom.getBillDate()));
            Integer count = bomService.addBom(bom, bomentries);
            msg = null;
            if(count > 0){
                //登录成功
                msg = new MessageRequest(200,"添加成功",null);
            }else {
                //登录失败
                msg = new MessageRequest(500,"添加失败",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = new MessageRequest(500,"添加失败",null);
        }
        return msg;
    }*/

    //序时簿 从首页跳转访问
    @RequestMapping("/OtherModelIndex")
    public String BomIndex(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                           @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize,
                           @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model,HttpServletRequest request){

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("startpage",(startpage - 1) * pagesize);
        map.put("pagesize",pagesize);
        map.put("AllQuery",AllQuery);
        int countTatol = otherModelService.getCounts(map);
        List<OtherModel> otherModels = otherModelService.OTHER_MODEL_Index(map);

        PageUtils<OtherModel> pageUtils = new PageUtils<OtherModel>(startpage, pagesize, countTatol, otherModels);

        model.addAttribute("static","jump");
        model.addAttribute("datas",pageUtils);
        return "desktop/OtherModelIndex";
    }

    //序时簿 从链接访问
    @RequestMapping("/requrl")
    public String BomIndexurl(@RequestParam(value = "startpage",defaultValue = "1") Integer startpage,
                           @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize,
                           @RequestParam(value = "AllQuery",defaultValue = "")String AllQuery,Model model,HttpServletRequest request){

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("startpage",(startpage - 1) * pagesize);
        map.put("pagesize",pagesize);
        map.put("AllQuery",AllQuery);
        int countTatol = otherModelService.getCounts(map);
        List<OtherModel> otherModels = otherModelService.OTHER_MODEL_Index(map);

        PageUtils<OtherModel> pageUtils = new PageUtils<OtherModel>(startpage, pagesize, countTatol, otherModels);

        model.addAttribute("datas",pageUtils);
        return "desktop/OtherModelIndex";
    }

    //显示详情
    @RequestMapping("/ShowRespData/{muid}")
    @ResponseBody
    public MessageRequest ShowBomGrade(@PathVariable("muid") int muid) {
        List<OtherModelEntry> otherModelEntries = otherModelService.GetDataByid(muid);

        MessageRequest msg = new MessageRequest(500,"正常",otherModelEntries);

        return msg;
    }


    /*
    1、前端页面
    2、后端处理请求
    3、target/classes目录下建public/upload
    <form class="am-form am-form-horizontal" id="img"
    action="/erp/img_add" method="post" enctype="multipart/form-data">
		<input type="file" id="name" required placeholder="商品图片" name="file" multiple>
		<!--<input type="button" onclick="imgsubmit()" class="am-btn am-btn-success" value="提交" />-->
		<input type="submit"  class="am-btn am-btn-success" value="提交" />
	</form>*/

    @RequestMapping("/img_add")
    @ResponseBody
    public MessageRequest addProduct(@RequestParam("file") MultipartFile[] files, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //response.setContentType("text/html;charset=utf-8");
        System.out.println("一共上传图片="+files.length);

        /*if (file.isEmpty()) {
            response.getWriter().write("<script>alert('上传的图片不能为空!');location.href='page_product';</script>");
            return null;
        }*/

        if (files == null || (files.length == 1 && files[0].getSize() == 0)){
            return new MessageRequest(250,"上传的图片不能为空",null);
        }

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            //上传的文件不为空
            String filename = file.getOriginalFilename();
            //获取文件的后缀
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //获取文件的名称 前缀名称
            String qz_nam = filename.substring(0,filename.lastIndexOf("."));
            //生成一个新的文件名
            filename = qz_nam+"_"+UUID.randomUUID() + suffixName;
            System.out.println("要上传服务器的文件名是:" + filename);
            //获取文件上传的路径
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "/public/upload/" + filename);
            file.transferTo(upload);
            System.out.println("文件上传成功:" + upload.getAbsolutePath());
        }



        MessageRequest msg = new MessageRequest(500,"正常",null);

        return msg;
    }

}
