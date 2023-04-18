package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity._order;
import com.lab.dblab4.entity.stall;
import com.lab.dblab4.entity.user;
import com.lab.dblab4.mapper._orderMapper;
import com.lab.dblab4.service._orderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class _orderServiceImpl implements _orderService {
    @Resource
    _orderMapper orderMapper;

    @Override
    public boolean add(_order order) {
        orderMapper.insert(order);
        return true;
    }

    @Override
    public List<_order> findAllByUser(user user) {
        return orderMapper.findAllByUser(user);
    }

    @Override
    public List<_order> findAllByUserId(int user_id) {
        user user = new user();
        user.setUser_id(user_id);
        return findAllByUser(user);
    }

    @Override
    public List<_order> findAllByUserIdStatus(int user_id, int status) {
        return orderMapper.findAllByUserIdStatus(user_id, status);
    }

    @Override
    public List<_order> findAllByStall(stall stall) {
        return orderMapper.findAllByStall(stall);
    }

    @Override
    public List<_order> findAllByStallStatus(stall stall, int status) {
        return orderMapper.findAllByStallStatus(stall.getStall_id(), status);
    }

    @Override
    public List<_order> findAllByStallId(int stall_id) {
        stall stall = new stall();
        stall.setStall_id(stall_id);
        return findAllByStall(stall);
    }

    @Override
    public void updateAcceptTime(int order_id) {
        orderMapper.updateAcceptTime(order_id);
    }

    @Override
    public void updateSendTime(int order_id) {
        orderMapper.updateSendTime(order_id);
    }

    @Override
    public void updateArriveTime(int order_id) {
        orderMapper.updateArriveTime(order_id);
    }

    @Override
    public _order findById(int order_id) {
        return orderMapper.findById(order_id);
    }

}
