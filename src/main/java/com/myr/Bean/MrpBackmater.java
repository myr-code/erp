package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MrpBackmater {

  private int fid;
  private String billNo;
  private String billDate;
  private int custId;
  private String mrpNo;
  private int entryId;
  private int itemId;
  private String custOrderNum;
  private String finishDate;
  private double standQty;
  private double qty;
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
