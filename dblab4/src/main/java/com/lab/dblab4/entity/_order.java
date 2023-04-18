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
public class _order {
    private int order_id;
    private int user_id;
    private int stall_id;
    private int address_id;
    private LocalDateTime order_create_time;
    private int order_price;
    /**
     * order_status:
     * 1:用户已提交，等待商家确认
     * 2:商家已确认，等待商家发货
     * 3:商家已发货，等待客户收货
     * 4:客户已收货
     */
    private short order_status;
    private LocalDateTime last_update;
    private LocalDateTime stall_accept_time;
    private LocalDateTime send_time;
    private LocalDateTime arrive_time;
}
