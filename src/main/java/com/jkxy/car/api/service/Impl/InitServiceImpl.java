package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.InitDao;
import com.jkxy.car.api.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author spxxkw
 * @date 2022/09/02
 */

@Service("init")
public class InitServiceImpl implements InitService {

    @Autowired
    private InitDao initDao;

    @Override
    @Transactional
    public void init() {
        initDao.dropTable1();
        initDao.dropTable2();
        initDao.createTable1();
        initDao.createTable2();
        initDao.insertInitDate1();
    }
}
