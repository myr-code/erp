package com.myr.Service.Impl;

import com.myr.Bean.MrpProductinstore;
import com.myr.Bean.MrpProductplan;
import com.myr.Mapper.mrp.Mrp_ProductPlanMapper;
import com.myr.Mapper.mrp.ProductInStoreMapper;
import com.myr.Service.ProductInStoreService;
import com.myr.Service.ProductPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class ProductInStoreServiceImpl implements ProductInStoreService {
    @Resource
    ProductInStoreMapper productInStoreMapper;

    @Override
    @Transactional
    public Integer add_ProductInStore(List<MrpProductinstore> mrpProductinstores) {
        int i = 0;
        for (MrpProductinstore mrpProductinstore : mrpProductinstores) {
            productInStoreMapper.add_ProductInStore(mrpProductinstore);
            i++;
        }
        return i;
    }

    @Override
    public String getBillNo(String dates) {
        return productInStoreMapper.getBillNo(dates);
    }

    @Override
    public List<MrpProductinstore> ProductInStore_page(Map<String, Object> map) {
        return productInStoreMapper.ProductInStore_page(map);
    }

    @Override
    public int getCounts(Map<String, Object> map) {
        return productInStoreMapper.getCounts(map);
    }

    @Override
    public List<MrpProductinstore> get_ProductInStoreById(Integer mid) {
        return productInStoreMapper.get_ProductInStoreById(mid);
    }

    @Override
    public Integer delProductInStore(String billNo) {
        return productInStoreMapper.delProductInStore(billNo);
    }

    @Override
    @Transactional
    public Integer ProductInStore_update(List<MrpProductinstore> mrpProductinstores) {
        int rs = 0;
        MrpProductinstore mrpProductinstore = null;
        if(mrpProductinstores!=null && mrpProductinstores.size()>0){
            mrpProductinstore = mrpProductinstores.get(0);

            if(productInStoreMapper.delProductInStore(mrpProductinstore.getBillNo())>0){
                for (MrpProductinstore productinstore : mrpProductinstores) {
                    productInStoreMapper.add_ProductInStore(productinstore);
                }
                rs = 1;
            }
        }

        return rs;
    }


    /*@Override
    @Transactional
    public Integer Mrp_ProductPlan_update(MrpProductplan mrpProductplan,List<MrpProductplan> mrpProductplans) {
        int rs = 0;
        if(mrpProductplans.size()>0&&mrpProductplans!=null){
            mrpProductplan = mrpProductplans.get(0);

            if(productPlanMapper.delMrpProductPlan(mrpProductplan.getBillNo())>0){
                for (MrpProductplan productplan : mrpProductplans) {
                    productPlanMapper.addMrp_ProductPlan(productplan);
                }
                rs = 1;
            }
        }

        return rs;
    }*/
}
