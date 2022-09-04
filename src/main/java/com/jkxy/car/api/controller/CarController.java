package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.dto.AddStockDto;
import com.jkxy.car.api.pojo.dto.BuyCarDto;
import com.jkxy.car.api.pojo.dto.FuzzyQueryDto;
import com.jkxy.car.api.pojo.model.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * @param dto
     * @return
     * @description 根据车辆id添加库存
     */
    @PostMapping("addStockById")
    public JSONResult addStockById(@RequestBody @Valid AddStockDto dto) {
        return carService.addStockById(dto);
    }

    /**
     * @param dto
     * @return
     * @description 购车
     */
    @PostMapping("buyCar")
    public JSONResult buyCar(@RequestBody @Valid BuyCarDto dto) {
        return carService.customerBuyCar(dto);
    }

    /**
     * @param dto
     * @return
     * @description 模糊查询car_customer表数据
     */
    @PostMapping("fuzzyQueryCarCustomerByKeyWord")
    public JSONResult fuzzyQueryCarCustomerByKeyWord(@RequestBody @Valid FuzzyQueryDto dto) {
        return carService.fuzzyQueryCarCustomerByKeyWord(dto);
    }

    /**
     * @param dto
     * @return
     * @description 模糊查询carmessage表数据
     */
    @PostMapping("fuzzyQueryCarByKeyWord")
    public JSONResult fuzzyQueryCarByKeyWord(@RequestBody @Valid FuzzyQueryDto dto) {
        return carService.fuzzyQueryCarByKeyWord(dto);
    }
}
