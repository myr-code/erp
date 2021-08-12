package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrder {

  private int fid;
  private String billNo;
  private String GSBillNo;
  private String billDate;
  private String type;
  private int custId;
  private String finishType;
  private int settleId;
  private String currencyName;
  private int rate;
  private String remark;
  private int depId;
  private int depStaffId;
  private int billStaffId;
  private int editStaffId;
  private int checkerId;
  private String createDate;
  private String checkDate;
  private String updateDate;

  //外键
  //订单体
  private SaleOrderEntry mid;
  //客户
  private Customer custIdFid;
  //结算方式
  private SettlementMethod settleIdFid;
  //部门
  private Department depIdFid;
  //职员 部门负责人
  private Staff depStaffIdFid;
  //职员 制单人
  private Staff billStaffIdFid;
  //职员 编辑人
  private Store stockId;
  /*//职员 审核人
  private Staff checkerId;*/

}
