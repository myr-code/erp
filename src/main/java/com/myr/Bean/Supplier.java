package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

  private Integer fid;
  private String code;
  private String name;
  private Integer cust_type_id;//类型
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
  private Integer department_id;//部门
  private Integer staff_id;//职员
  private String other_remark;
  private Integer settlement_method_id;//结算方式
  private String create_datetime;
  private String update_datetime;
  private Integer fdisable;

  //外键
  private String cust_type_idName;//类型 名称
  private String department_idName;//部门 名称
  private String staff_idName;//职员 名称
  private String settlement_method_idName;//结算方式 名称

}
