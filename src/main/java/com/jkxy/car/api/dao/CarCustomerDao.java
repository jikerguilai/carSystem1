package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.model.CarCustomerModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author spxxkw
 * @date 2022/09/01
 */

@Mapper
@Component
public interface CarCustomerDao {

    int insertCarInfo(@Param("model") CarCustomerModel model);

    List<CarCustomerModel> getStockCar(@Param("carSeries") String carSeries);

    int buyCar(@Param("model") CarCustomerModel model);

    List<CarCustomerModel> getStockByIds(@Param("list") List<Integer> ids);

}
