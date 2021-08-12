package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bom {

  private int fid;
  private String billNo;
  private int muid;
  private String itemCode;
  private String itemName;
  private String itemModel;
  private String custItemCode;
  private String custItemModel;
  private String unitName;
  private int qty;
  private String billDate;
  private int DefCustId;
  private double ArtificialCost;
  private double materialCost;
  private double allCost;
  private String remark;
  private String createDate;
  private String editDate;
  private int billStaf;
  private int fdisable;

  //外键
  private Bomentry muidFid;

  private Customer defCustIdFid;

  private Staff billStafFid;

}
