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
public class OtherModel {
    private Integer fid;
    private String url;
    private String req_type;
    private String resultFormat;
    private String remark;

}
