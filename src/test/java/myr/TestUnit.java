package myr;

import com.myr.APP;
import com.myr.Bean.Item;
import com.myr.Bean.Unit;
import com.myr.Service.ItemService;
import com.myr.Service.UnitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class TestUnit {
    @Resource
    UnitService unitService;

    @Resource
    ItemService itemService;

    @Test
    public void testitem(){
        List<Item> items = itemService.Item_page("");
        for (Item item : items) {
            System.out.println(item);
        }

    }

    @Test
    public void testitemquerycnm(){
        /*List<Item> items = itemService.Item_queryByCNM("");
        for (Item item : items) {
            System.out.println(item);
        }*/

    }

    @Test
    public void testadd(){
        Unit unit = new Unit();
        unit.setName("个");
        Integer count = unitService.addUnit(unit);
        System.out.println(count);
    }
}
