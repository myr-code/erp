package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MrpCalcTemp {
  private long mrp_id;
  private int sos_fid;
  private int main_item_id;
  private int child_item_id;
  private double demand_qty;
  private double demand_zh_qty;
  private String create_date;

}
