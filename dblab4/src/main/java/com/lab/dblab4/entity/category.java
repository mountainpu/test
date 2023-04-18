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
public class category {
    private int category_id;
    private int stall_id;
    private String category_name;
    private LocalDateTime last_update;
}
