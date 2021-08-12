package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemType {

  private int fid;
  private String name;
  private String remark;
  private String createDate;
  private String updateDate;
  private int fdisable;

}
