package com.lab.dblab4.mapper;

import com.lab.dblab4.entity._order;
import com.lab.dblab4.entity.stall;
import com.lab.dblab4.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface _orderMapper {
    // 查看某用户全部订单
    List<_order> findAllByUser(user user);
    // 查看某商户全部订单
    List<_order> findAllByStall(stall stall);
    // 更新某订单
    void updateAcceptTime(int order_id);
    void updateSendTime(int order_id);
    void updateArriveTime(int order_id);
    void insert(_order order);
    _order find(_order order);
    _order findById(int order_id);
    List<_order> findAllByUserIdStatus(@Param("user_id") int user_id, @Param("status") int status);
    List<_order> findAllByStallStatus(@Param("stall_id") int stall_id, @Param("status") int status);
}
