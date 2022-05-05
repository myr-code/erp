package com.myr.Bean.othermodel;

import com.myr.Bean.CustType;
import com.myr.Bean.Department;
import com.myr.Bean.SettlementMethod;
import com.myr.Bean.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCrm {

  private Integer fid;
  private String code;
  private String name;
  private Integer cust_type_id;
  private String abb;
  private String intelnetUrl;//网站地址
  private String address;
  private String contact;
  private String phone;
  private String fax;
  private String postcode;
  private String postcode_address;
  private String zycylx;//主要承运路线
  private String zycycp;//主要承运产品
  private String bank_account;
  private String open_account_bank;
  private String tax_rate_number;
  private Integer vat_rate;
  private Integer department_id;
  private Integer staff_id;
  private String other_remark;
  private String other_remark2;
  private String other_remark3;
  private Integer settlement_method_id;
  private String create_datetime;
  private String update_datetime;
  private Integer fdisable;

  private CustType cust_type_idFid;//类型
  private Department department_idFid;//部门
  private Staff staff_idFid;//职员
  private SettlementMethod settlement_method_idFid;//结算方式
}
