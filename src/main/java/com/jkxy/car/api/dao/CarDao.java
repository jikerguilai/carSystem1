package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

//数据库的操作

@Mapper
public interface CarDao {
    @Select("select * from carMessage") //注解对应下面的
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName); //根据车名查询

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries},carNum=#{carNum} where id = #{id}")
    void updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries,carNum) values(#{carName},#{carType},#{price},#{carSeries},#{carNum})")
    void insertCar(Car car);

    @Select("select * from carMessage where carName like '%${query}%' order by id limit #{start} , #{end}")
    List<Car> fuzzyQueries(String query,int start,int end);
}
