package com.myr.Service.Impl.othermodel;

import com.myr.Bean.MrpPurReq;
import com.myr.Bean.othermodel.CustomerCrm;
import com.myr.Bean.othermodel.OrderCrm;
import com.myr.Bean.othermodel.Urlcontent;
import com.myr.Mapper.mrp.PurReqMapper;
import com.myr.Mapper.othermodel.OrderCrmMapper;
import com.myr.Service.PurReqService;
import com.myr.Service.othermodel.OrderCrmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OrderCrmServiceImpl implements OrderCrmService {
    @Resource
    OrderCrmMapper orderCrmMapper;

    @Override
    @Transactional
    public Integer add_OrderCrm(List<OrderCrm> orderCrms) {
        int count = 0;
        for (OrderCrm orderCrm : orderCrms) {
            count += orderCrmMapper.add_OrderCrm(orderCrm);
        }
        System.out.println(count);
        if(count == orderCrms.size()){
            return 1;//成功
        }else{
            return -1;
        }
    }

    @Override
    public String getBillNo(String dates) {
        return orderCrmMapper.getBillNo(dates);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return orderCrmMapper.getCounts(map);
    }

    @Override
    public List<OrderCrm> OrderCrm_page(Map<String, Object> map) {
        return orderCrmMapper.OrderCrm_page(map);
    }

    @Override
    public Integer delOrderCrm(String billNo) {
        return orderCrmMapper.delOrderCrm(billNo);
    }

    @Override
    public List<OrderCrm> getOrderCrmById(String billNo) {
        return orderCrmMapper.getOrderCrmById(billNo);
    }

    @Override
    public List<CustomerCrm> CustCrm_All() {
        return orderCrmMapper.CustCrm_All();
    }

    @Override
    public Integer add_UrlContent(List<Urlcontent> urlcontents) {
        for (Urlcontent urlcontent : urlcontents) {
            orderCrmMapper.add_UrlContent(urlcontent);
        }
        return 1;
    }
}
