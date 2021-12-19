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
  private String billDate;
  private String mrpNo;
  private int custId;
  private String currencyName;
  private String address;
  private String contact;
  private String phone;
  private String settleName;
  private String remark;
  private int billYear;
  private int billPeriod;
  private int itemId;
  private int stockId;
  private String unitName;
  private String saleOrderBillNo;
  private double saleOrderQty;
  private double saleOutQty;
  private double taxPriceNo;
  private String skph;
  private String voucheNumber;
  private double ykpAmt;
  private double wkpAmt;
  private double yjsAmt;
  private double wjsAmt;
  private int isDZ;
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


}
