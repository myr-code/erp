package myr;

import com.myr.APP;
import com.myr.Bean.Customer;
import com.myr.Bean.User;
import com.myr.Service.CustomerService;
import com.myr.Service.UserService;
import com.myr.utils.PinYinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class TestCustomer {
    @Resource
    CustomerService customerService;

    @Test
    public void testcustomer(){
        Customer c = new Customer();
        c.setName("123");
        Integer count = customerService.addCustomer(c);
        System.out.println(count);
    }

    @Test
    public void testgetcustbyid(){
        Customer custById = customerService.getCustById(6);
        System.out.println(custById);
    }

    @Test
    public void del(){
        Integer count = customerService.delCust(100);
        System.out.println(count);
    }

    @Test
    public void page(){
        /*List<Customer> w = customerService.Customer_page("w");
        for (Customer customer : w) {
            System.out.println(customer);
        }*/
    }

    @Test
    public void isexits(){
        Customer c = new Customer();
        c.setFid(-1);
        c.setName("3ef");
        Integer isexits = customerService.isexits(c);
        System.out.println(isexits);
    }



}
