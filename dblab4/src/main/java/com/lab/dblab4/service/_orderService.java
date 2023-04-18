package com.lab.dblab4.service;

import com.lab.dblab4.entity._order;
import com.lab.dblab4.entity.stall;
import com.lab.dblab4.entity.user;

import java.time.LocalDateTime;
import java.util.List;

public interface _orderService {
    boolean add(_order order);
    List<_order> findAllByUser(user user);
    List<_order> findAllByUserId(int user_id);
    List<_order> findAllByUserIdStatus(int user_id, int status);
    List<_order> findAllByStall(stall stall);
    List<_order> findAllByStallStatus(stall stall, int status);
    List<_order> findAllByStallId(int stall_id);
    void updateAcceptTime(int order_id);
    void updateSendTime(int order_id);
    void updateArriveTime(int order_id);
    _order findById(int order_id);
}
