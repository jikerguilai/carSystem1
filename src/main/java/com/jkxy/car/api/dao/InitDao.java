package com.jkxy.car.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author spxxkw
 * @date 2022/09/02
 * @description 初始化
 */

@Mapper
@Component
public interface InitDao {

    int dropTable1();

    int dropTable2();

    int createTable1();

    int createTable2();

    int insertInitDate1();

}
