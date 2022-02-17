package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SFK {

  private int fid;
  private String billNo;
  private String billType;
  private String billDate;
  private int custId;
  private String settleName;
  private String currencyName;
  private int rate;
  private double exchangeRate;
  private String skAccount;
  private double skAmt;
  private String zkAccount;
  private double zkAmt;
  private String fyAccount;
  private double fyAmt;
  private double hxAmtTotal;
  private String remark;
  private int sourFid;
  private String sourType;
  private String sourBillNo;
  private int sourEntryId;
  private String sourBillDate;
  private int entryId;
  private int itemId;
  private String custOrderNum;
  private double qty;
  private double taxPrice;
  private double taxAmt;
  private double hxAmt;
  private String rowRemark;
  private int billStaffId;
  private int billCheckId;
  private String checkDate;
  private String createDate;
  private String editDate;

  private Customer custIds;
  private Item itemIds;

}
