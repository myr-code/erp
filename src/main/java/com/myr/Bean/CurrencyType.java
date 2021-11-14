package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyType {

  private int fid;
  private String name;
  private double exchangeRate;
  private String remark;
  private String createDate;
  private String updateDate;
  private int fdisable;

}
