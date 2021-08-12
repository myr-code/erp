package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bomentry {

  private int fid;
  private int mid;
  private int entryId;
  private int muid;
  private int mqty;
  private int cuid;
  private String itemCode;
  private String itemName;
  private String itemModel;
  private String custItemCode;
  private String custItemModel;
  private String unitName;
  private int qty;
  private double taxPrice;
  private double taxAmt;
  private int DefSuppId;
  private String DefSuppName;
  private String rowRemark;

  //外键
  private Supplier defSuppIdFid;

}
