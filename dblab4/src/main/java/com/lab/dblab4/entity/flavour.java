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
public class flavour {
    private int flavour_id;
    private int dish_id;
    private String flavour_desp;
    private LocalDateTime last_update;
}
