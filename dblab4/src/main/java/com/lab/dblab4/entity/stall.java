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
public class stall {
    private int stall_id;
    private int user_id;
    private int canteen_id;
    private String stall_name;
    private String stall_desp;
    private LocalDateTime last_update;
}
