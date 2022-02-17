package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private int fid;
  private String accountId;
  private String accountName;
  private String accountType;
  private String remark;


}
