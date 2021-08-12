package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
职员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

  private int fid;
  private String code;
  private String name;
  private int departmentId;
  private int postId;
  private int staffTypeId;
  private String gender;
  private String phone;
  private String chinaId;
  private String entryDate;
  private String departureDate;
  private int educationId;
  private String remark;
  private String createDatetime;
  private String updateDatetime;
  private int fdisable;

  //外键
  private String departmentIdName;

}
