package com.jkxy.car.api.service;

import com.jkxy.car.api.pojo.dto.BuyCarDto;
import com.jkxy.car.api.pojo.dto.FuzzyQueryDto;
import com.jkxy.car.api.pojo.model.Car;
import com.jkxy.car.api.pojo.dto.AddStockDto;
import com.jkxy.car.api.utils.JSONResult;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    void deleteById(int id);

    void updateById(Car car);

    void insertCar(Car car);

    JSONResult addStockById(AddStockDto dto);

    JSONResult customerBuyCar(BuyCarDto dto);

    JSONResult fuzzyQueryCarCustomerByKeyWord(FuzzyQueryDto dto);

    JSONResult fuzzyQueryCarByKeyWord(FuzzyQueryDto dto);

}
