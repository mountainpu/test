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
public class canteen {
    private int canteen_id;
    private int user_id;
    private int address_id;
    private String canteen_name;
    private LocalDateTime last_update;
}
