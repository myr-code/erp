package com.myr.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer fid;
    private String name;
    private String email;
    private String password;

}
