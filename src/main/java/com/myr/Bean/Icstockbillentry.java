package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Icstockbillentry {

  private int fid;
  private int mid;
  private int entryId;
  private int itemId;
  private String itemCode;
  private String itemName;
  private String itemModel;
  private String custItemCode;
  private String custItemModel;
  private String unitName;
  private String custOrderNum;
  private double qty;
  private int stockId;
  private String batchNumber;
  private double taxPrice;
  private double taxPriceNo;
  private int fcess;
  private double taxAmt;
  private double taxAmtNo;
  private double fcessAmt;
  private int fdisable;
  private int stockType;
  private String rowRemark;
  private int sourFid;
  private String sourBillNo;
  private int sourEntryId;
  private String sourType;

  private double hxAmt;
}
