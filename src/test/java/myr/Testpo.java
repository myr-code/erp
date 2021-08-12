package myr;

import com.myr.APP;
import com.myr.Bean.Poorder;
import com.myr.Bean.Poorderentry;
import com.myr.Bean.SaleOrder;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Service.PurOrderEntryService;
import com.myr.Service.PurOrderService;
import com.myr.Service.SaleOrderEntryService;
import com.myr.Service.SaleOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class Testpo {
    @Resource
    PurOrderService purOrderService;

    @Resource
    PurOrderEntryService purOrderEntryService;

    
    
    @Test
    public void testadd(){
        List<Poorder> Poorders = purOrderService.PurOrder_page("0304");
        for (Poorder Poorder : Poorders) {
            System.out.println(Poorder);
        }
    }

    @Test
    public void testadd2(){
        List<Poorderentry> pOentryById = purOrderEntryService.getPOentryById(3);
        for (Poorderentry poorderentry : pOentryById) {
            System.out.println(poorderentry);
        }
    }


}
