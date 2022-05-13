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
  private double oneVolume;
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

  public void setItemImg(String itemImg) {
    if(itemImg.length() > 0){
      if(itemImg.length() - itemImg.replace(",", "").length() == 0){
        this.itemImg = "共1个文件";
      }else {
        int count  = (itemImg.length() - itemImg.replace(",", "").length())+1;
        this.itemImg = "共"+count+"个文件";
      }
    }else {
      this.itemImg = "共0个文件";
    }

  }
}
