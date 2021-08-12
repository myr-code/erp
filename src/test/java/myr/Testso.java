package myr;

import com.myr.APP;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Service.SaleOrderEntryService;
import com.myr.Service.SaleOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class Testso {
    @Resource
    SaleOrderService saleOrderService;

    @Resource
    SaleOrderEntryService saleOrderEntryService;

    @Test
    public void testadd(){
        List<SaleOrderEntry> sOentryById = saleOrderEntryService.getSOentryById(27);
        for (SaleOrderEntry saleOrderEntry : sOentryById) {
            System.out.println(saleOrderEntry);
        }
    }

    @Test
    public void testgetbillno(){
        String billNo = "saleOrderService.getBillNo()";
        System.out.println(billNo);
    }

    @Test
    public void testso(){
        SaleOrder s = new SaleOrder();
        s.setGSBillNo("test007");
        List<SaleOrderEntry> soentrys = new ArrayList<>();
        Integer count = saleOrderService.SaleOrder_update(s);
        System.out.println(count);
    }

    @Test
    public void testurl(){
        String htmlContent = getHtmlContent("https://www.alibaba.com/?__redirected__=1");
        System.out.println("文本="+htmlContent);
    }

    public String getHtmlContent(String htmlurl) {
        URL url;
        String temp;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(htmlurl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));// 读取网页全部内容
            while ((temp = in.readLine()) != null) {
                sb.append(temp);
            }
            in.close();
        } catch (final MalformedURLException me) {
            System.out.println("你输入的URL格式有问题!");
            me.getMessage();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
