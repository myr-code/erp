package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
部门
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

  private Integer fid;
  private String code;
  private String name;
  private Integer staffId;
  private String remark;
  private String createDate;
  private String updateDate;
  private Integer fdisable;

  //外键name
  private String staffIdName;


}
