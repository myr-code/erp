package com.myr.Bean.othermodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Urlcontent {

  private String url;
  private Integer rows;
  private String rowContent;

}
