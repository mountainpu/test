package com.lab.dblab4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class dish_info {
    private int dish_id;
    private int category_id;
    private String dish_name;
    private double price;
    private String dish_desp;
    private int inventory;
    private LocalDateTime last_update;
    private String category_name;
    private String stall_name;
    private String canteen_name;
    private List<flavour> flavours;
}
