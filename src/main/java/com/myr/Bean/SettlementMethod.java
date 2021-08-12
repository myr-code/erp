package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementMethod {

  private int fid;
  private String name="";
  private int settlementDays;
  private int reconciliationDay;
  private int graceDays;
  private String remark;
  private String createDate;
  private String updateDate;
  private int fdisable;

  public void setSettlementDays(int settlementDays) {
    this.settlementDays = settlementDays;
  }

  public void setReconciliationDay(int reconciliationDay) {
    this.reconciliationDay = reconciliationDay;
  }

  public void setGraceDays(int graceDays) {
    this.graceDays = graceDays;
  }
}
