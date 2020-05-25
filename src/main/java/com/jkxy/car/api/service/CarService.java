package com.jkxy.car.api.service;

import com.jkxy.car.api.pojo.Car;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    void deleteById(int id);

    void updateById(Car car);

    void insertCar(Car car);

    boolean buyCar(int id,int carNum); //根据车的编号和数量买车
    List<Car> fuzzyQueries(String query,int start,int end); //模糊查询
    //选择条数进行展示  五条
    //能不能做个表单
    //int countRows(String carName); //满足条件的行数
}
