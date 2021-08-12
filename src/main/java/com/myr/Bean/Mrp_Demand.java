package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mrp_Demand {
    private int sosfid;//源订单fid
    private int itemId;//主件id
    private String mainItemCode;//主件名称
    private int cuid;
    private String code="";
    private String name="";
    private String model="";
    private String unitName="";
    private double demand_qty=0;
    private double way_qty=0;
    private double wait_pro_qty=0;
    private double stock_qty=0;

}
