package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MrpProductinstore {

  private int fid;
  private String billNo;
  private String billDate;
  private int finalStoreId;
  private Supplier suppId;
  private String billType;
  private int typeId;
  private int entryId;
  private Item itemId;
  private String custOrderNum;
  private String finishDate;
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
  private String remark;
  private String rowRemark;
  private int finishQty;
  private int finishStatic;
  private int sourFid;
  private String sourBillNo;
  private int sourEntryId;
  private String sourType;
  private int billStaf;
  private String createDate;
  private String editDate;

}
