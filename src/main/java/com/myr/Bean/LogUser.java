package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogUser {

  private Integer fid;
  private String type;
  private String user;
  private String object;
  private String userIp;
  private String createtime;

}
