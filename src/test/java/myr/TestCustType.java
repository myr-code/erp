package myr;

import com.myr.APP;
import com.myr.Bean.CustType;
import com.myr.Bean.Customer;
import com.myr.Service.CustTypeService;
import com.myr.Service.CustomerService;
import com.myr.utils.PinYinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APP.class)
public class TestCustType {
    @Resource
    CustTypeService custTypeService;

    @Test
    public void testcusttype(){
        String type = "1";
        List<CustType> custTypes = custTypeService.custType_all(Integer.parseInt(type));
        for (CustType custType : custTypes) {
            System.out.println(custType);
        }
    }


}
