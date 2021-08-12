package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
客户分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustType {

  private Integer fid;
  private String name;
  private Integer type;
  private String other_remark;
  private String create_datetime;
  private String update_datetime;
  private Integer fdisable;

}
