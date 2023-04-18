package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.canteen;
import com.lab.dblab4.mapper.addressMapper;
import com.lab.dblab4.mapper.canteenMapper;
import com.lab.dblab4.service.canteenService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class canteenServiceImpl implements canteenService {

    @Resource
    canteenMapper canteenMapper;

    @Resource
    addressMapper addressMapper;

    @Override
    public List<canteen> findAll() {
        return canteenMapper.findAll();
    }

    @Override
    public List<canteen> findAllByUserId(int user_id) {
        return canteenMapper.myCanteen(user_id);
    }

    @Override
    public canteen find(canteen canteen) {
        return canteenMapper.find(canteen);
    }

    @Override
    public boolean add(canteen canteen) {
        canteenMapper.insert(canteen);
        return true;
    }

    @Override
    public boolean update(canteen canteen) {
        canteenMapper.update(canteen);
        return true;
    }

    @Override
    public List<address> findMyCanteenAddress(int user_id) {
        List<canteen> myCanteen = canteenMapper.myCanteen(user_id);
        List<address> res = new ArrayList<>();
        for (canteen canteen : myCanteen){
            res.add(addressMapper.findById(canteen.getAddress_id()));
        }
        return res;
    }

    @Override
    public canteen findById(int canteen_id) {
        canteen canteen = new canteen();
        canteen.setCanteen_id(canteen_id);
        return canteenMapper.find(canteen);
    }
}
