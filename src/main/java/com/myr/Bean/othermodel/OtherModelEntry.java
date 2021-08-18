package com.myr.Bean.othermodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
其他功能
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherModelEntry {
    private Integer fid;
    private Integer mid;
    private String par1="";
    private String par2="";
    private String par3="";
    private String par4="";
    private String par5="";
    private String row_remark="";

}
