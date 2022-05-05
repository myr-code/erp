package com.myr.Bean.othermodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCrm {

  private Integer fid;
  private String billNo;
  private String billDate;
  private String custName;
  private Integer entryId;
  private String sendAddress;
  private String collAddress;
  private String item;
  private String itemImg;
  private double oneWeight;
  private String oneSize;
  private double oneVolume0;
  private double qty;
  private double weightSum;
  private double volumeSum;
  private String purpose;
  private String bjhd;
  private String remark;
  private String rowRemark;
  private Integer billStaf;
  private String createDate;
  private String editDate;

}
