package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

//服务层

@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public boolean buyCar(int id ,int carNum){
        Car temp = carDao.findById(id); //找到
        //System.out.println("当前车数"+temp.getCarNum());
        if (temp.getCarNum()<carNum){
            System.out.println("车辆不足！！！");
            return false;
            //JSONResult.errorMsg("车辆不足！！！");
        }else{
            ReentrantLock lock = new ReentrantLock();
            //防止获取没被修改的数据
            temp.setCarNum(temp.getCarNum()-carNum); //修改车的数量
            lock.lock(); //加锁
            carDao.updateById(temp);
            lock.unlock();
            System.out.println("买车完成！！！");
            System.out.println("当前车数"+temp.getCarNum());
        }
        return true;
    }//重写买车方法

    @Override
    public List<Car> fuzzyQueries(String query,int start,int end){
        return carDao.fuzzyQueries(query,start,end);
    }
}
