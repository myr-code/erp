package myr;

import com.myr.APP;
import com.myr.Bean.Icstockbill;
import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Service.ICStockBillEntryService;
import com.myr.Service.IcStockBillService;
import com.myr.Service.PurOrderEntryService;
import com.myr.Service.PurOrderService;
import com.myr.utils.DateOption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class Testic {
    @Resource
    IcStockBillService icStockBillService;

    @Resource
    ICStockBillEntryService icStockBillEntryService;

    @Test
    public void test(){
        Icstockbill icstockbill = new Icstockbill();
        DateOption dateOption = new DateOption();
        dateOption.setData(icstockbill);
        List<Icstockbill> icstockbills = icStockBillService.IcStockBill_pageGj(dateOption);
        for (Icstockbill icstockbill1 : icstockbills) {
            System.out.println(icstockbill);
        }
    }

    @Test
    public static void main(String[] args) {
        String datestring_start = new SimpleDateFormat("YYYY-MM").format(new Date())+"-01";
        String datestring_end = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        System.out.println(datestring_start);
        System.out.println(datestring_end);
    }



}
