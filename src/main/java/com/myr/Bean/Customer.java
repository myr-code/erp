package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
客户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer fid;
    private String code;
    private String name;
    private int cust_type_id;
    private String abb;
    private String address;
    private String contact;
    private String phone;
    private String fax;
    private String postcode;
    private String postcode_address;
    private String bank_account;
    private String open_account_bank;
    private String tax_rate_number;//税率登记号
    private Integer vat_rate;
    private int department_id;//部门
    private int staff_id;//职员
    private String other_remark;
    private int settlement_method_id;//结算方式
    private String create_datetime;
    private String update_datetime;
    private Integer fdisable;

    private CustType cust_type_idFid;//类型
    private Department department_idFid;//部门
    private Staff staff_idFid;//职员
    private SettlementMethod settlement_method_idFid;//结算方式
}
