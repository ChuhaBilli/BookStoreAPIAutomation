package com.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginContract {

    private String email;
    private String password;

}
