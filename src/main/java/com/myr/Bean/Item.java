package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  private int fid;
  private String code="";
  private String name="";
  private String model="";
  private String specs="";
  private String parameter="";
  private String custItemCode="";
  private String custItemName="";
  private String custItemModel="";
  private String custItemSpecs="";
  private int itemTypeId;
  private int purUnitId;
  private int saleUnitId;
  private double purPrice;
  private double salePrice;
  private int defaultSupplierId;
  private int defaultCustomerId;
  private double safeStock;
  private int defaultStorehouseId;
  private int isstock;
  private int depId;
  private int staffId;
  private String code1="";
  private String code2="";
  private String remark="";
  private String createDate="";
  private String updateDate="";
  private int fdisable;

  //外键name
  private String itemTypeIdName="";
  private String purUnitIdName="";
  private String saleUnitIdName="";
  private String defaultSupplierIdName="";
  private String defaultCustomerIdName="";
  private String defaultStorehouseIdName="";
  private String depIddName="";
  private String staffIdName="";

  private Unit saleUnitIdFid;

}
