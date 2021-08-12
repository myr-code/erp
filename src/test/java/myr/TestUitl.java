package myr;

import com.myr.APP;
import com.myr.Bean.SaleOrderEntry;
import com.myr.Bean.User;
import com.myr.Service.UserService;
import com.myr.utils.GetParValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class TestUitl {
    @Resource
    UserService userService;

    @Test
    public void testuser(){
        User user = new User();
        user.setEmail("1270710614@qq.com");
        user.setPassword("123");
        User u = userService.getUserByName(user);
        System.out.println(u);
    }

    @Test
    public void test2() {

        List<String> childerField = GetParValues.getChilderField(SaleOrderEntry.class);
        System.out.println(childerField.size());
        for (String s : childerField) {
            System.out.println(s);
        }
    }



}
