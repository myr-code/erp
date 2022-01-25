package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dz {

  private int fid;
  private String billNo;
  private String billType;
  private String billDate;
  private String mrpNo;
  private int custId;
  private String currencyName;
  private String address;
  private String contact;
  private String phone;
  private String settleName;
  private int rate;
  private String remark;
  private int billYear;
  private int billPeriod;
  private int EntryId;
  private int itemId;
  private int stockId;
  private String unitName;
  private String custOrderNum;
  private String batchNumber;
  private String saleOrderBillNo;
  private double saleOrderQty;
  private double saleOutQty;
  private double taxPrice;
  private double taxPriceNo;
  private double taxAmt;
  private double taxAmtNo;
  private String skph;
  private String voucheNumber;
  private double ykpAmt;
  private double wkpAmt;
  private double yjsAmt;
  private double wjsAmt;
  private int isDZ;
  private String rowRemark;
  private int sourFid;
  private String sourBillNo;
  private int sourEntryId;
  private String sourType;
  private int dzCheckId;
  private String dzCheckDate;
  private int billStaffId;
  private int billCheckId;
  private String createDate;
  private String editDate;

  //外键
  private Customer custIds;//客户
  private Item itemIds;//产品
  private Store stockIds;//仓库
}
