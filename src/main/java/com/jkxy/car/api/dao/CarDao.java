package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.model.Car;
import com.jkxy.car.api.pojo.model.CarCustomerModel;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CarDao {
    @Select("select * from carMessage")
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName);

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries} where id = #{id}")
    void updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries) values(#{carName},#{carType},#{price},#{carSeries})")
    void insertCar(Car car);

    @Update("update carMessage set stock=#{stock} where id=#{id}")
    int updateCarStockById(Integer id, Integer stock);

    @Select("select * from carMessage where carSeries=#{carSeries}")
    Car findByCarSeries(String carSeries);


}
