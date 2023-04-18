package com.lab.dblab4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class user {
    private int user_id;
    private String username;
    private int user_type;
    private String password;
    private String cellphone;
    private LocalDateTime user_create_time;
    private LocalDateTime last_update;
}
