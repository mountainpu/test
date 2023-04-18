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
public class dish {
    private int dish_id;
    private int category_id;
    private String dish_name;
    private double price;
    private String dish_desp;
    private int inventory;
    private LocalDateTime last_update;
}
