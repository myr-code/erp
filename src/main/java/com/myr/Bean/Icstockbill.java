package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Icstockbill {

  private int fid;
  private String billNo;
  private String billDate;
  private int suppId;
  private String billType;
  private String type;
  private int settId;
  private String currName;
  private int rate;
  private String address;
  private String contact;
  private String phone;
  private String remark;
  private int depaId;
  private int saleStaf;
  private int billStaf;
  private int editStaf;
  private int checkStaf;
  private String createDate;
  private String editDate;
  private String checkDate;
  private String state;
  private int fdisable;

  //外键
  //订单体
  private Icstockbillentry mid;
  //供应商
  private Supplier suppIdFid;
  //结算方式
  private SettlementMethod settIdFid;
  //部门
  private Department depaIdFid;
}
