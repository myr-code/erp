package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poorderentry {

  private int fid;
  private int mid;
  private int entryId;
  private int itemId;
  private String itemCode="";
  private String itemName="";
  private String itemModel="";
  private String custItemCode="";
  private String custItemModel="";
  private String unitName="";
  private String custOrderNum="";
  private String finishDate="";
  private double qty;
  private String batchNumber="";
  private double taxPrice;
  private double taxPriceNo;
  private int fcess;
  private double taxAmt;
  private double taxAmtNo;
  private double fcessAmt;
  private int fdisable;
  private String rowRemark="";
  /*private String source;*/
  private int sourFid;
  private String sourBillNo;
  private int sourEntryId;
  private String sourType;
  private int icsQty;//已关联数量

  //外键
  /*private Item itemIdFid;*/

}
