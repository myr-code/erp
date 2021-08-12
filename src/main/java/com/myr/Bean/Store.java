package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {

  private int fid;
  private String name="";
  private int isworkshop;
  private int relativeSupplierId;
  private String remark;
  private String createDate;
  private String updateDate;
  private int fdisable;

  //#外键
  private String relativeSupplierIdName;

}
