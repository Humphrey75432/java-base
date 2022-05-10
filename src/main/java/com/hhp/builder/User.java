package com.hhp.builder;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String remark;
}
