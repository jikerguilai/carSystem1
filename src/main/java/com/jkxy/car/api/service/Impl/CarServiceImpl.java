package com.jkxy.car.api.service.Impl;

import com.alibaba.druid.util.StringUtils;
import com.jkxy.car.api.dao.CarCustomerDao;
import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.dto.AddStockDto;
import com.jkxy.car.api.pojo.dto.BuyCarDto;
import com.jkxy.car.api.pojo.dto.FuzzyQueryDaoDto;
import com.jkxy.car.api.pojo.dto.FuzzyQueryDto;
import com.jkxy.car.api.pojo.model.Car;
import com.jkxy.car.api.pojo.model.CarCustomerModel;
import com.jkxy.car.api.pojo.vo.FuzzyQueryVo;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Autowired
    CarCustomerDao carCustomerDao;

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
    @Transactional
    public JSONResult addStockById(AddStockDto dto) {
        Integer carId = dto.getId();
        Integer carStock = dto.getAddStock();
        if (0 > carStock) {
            return JSONResult.errorParamMsg("添加车辆数不能小于0");
        }

        // 车辆记录是否存在于库校验
        Car objCar = carDao.findById(carId);
        if (null == objCar) {
            return JSONResult.blankDatabaseMsg("未记录该车辆");
        }

        // 给车辆添加库存数量
        carDao.updateCarStockById(carId, carStock + objCar.getStock());

        // 将新车添加到car_customer表中
        CarCustomerModel model = new CarCustomerModel();
        model.setCarSeries(objCar.getCarSeries());
        model.setCarName(objCar.getCarName());
        for (int i = 0; i < carStock; i++) {
            carCustomerDao.insertCarInfo(model);
        }
        return JSONResult.ok();
    }

    @Override
    @Transactional
    public JSONResult customerBuyCar(BuyCarDto dto) {
        String customer = dto.getCustomer();
        String carSeries = dto.getCarSeries();
        Integer count = dto.getCount();
        if (StringUtils.isEmpty(customer)) {
            return JSONResult.errorParamMsg("请输入有效的顾客姓名");
        }
        if (StringUtils.isEmpty(carSeries)) {
            return JSONResult.errorParamMsg("请输入意向车型");
        }
        if (0 >= count) {
            return JSONResult.errorParamMsg("购买数量要大于等于0");
        }
        Car aimCar = carDao.findByCarSeries(carSeries);
        if (null == aimCar || 0 >= aimCar.getStock()) {
            return JSONResult.errorResultMsg("库存中暂无" + carSeries + "车型");
        }
        if (0 > aimCar.getStock() - count) {
            return JSONResult.errorResultMsg("库存少于购买数量，无法购买");
        }
        List<CarCustomerModel> stockCar = carCustomerDao.getStockCar(carSeries);
        if (null == stockCar || 0 == stockCar.size()) {
            return JSONResult.errorResultMsg("库存中暂无" + carSeries + "车型");
        }
        if (aimCar.getStock() != stockCar.size()) {
            return JSONResult.errorResultMsg("车辆库存数与剩余记录不匹配，建议检查数据，或初始化系统");
        }

        List<Integer> ids = new ArrayList<>();

        // 确定人车关系
        for (int i = 0; i < count; i++) {
            CarCustomerModel customerBuy = stockCar.get(i);
            customerBuy.setCustomer(customer);
            customerBuy.setSold("1");
            carCustomerDao.buyCar(customerBuy);
            ids.add(customerBuy.getId());
        }
        // 记录库存
        carDao.updateCarStockById(aimCar.getId(), aimCar.getStock() - count);

        // 返回数据
        List<CarCustomerModel> returnList = carCustomerDao.getStockByIds(ids);

        return JSONResult.ok(returnList);
    }

    @Override
    public JSONResult fuzzyQueryCarCustomerByKeyWord(FuzzyQueryDto dto) {
        String keyWord = dto.getKeyWord();
        Integer pageSize = dto.getPageSize();
        Integer pageNum = dto.getPageNum();
        Integer total = 0;
        JSONResult result = this.checkParam(dto);
        if (null != result) {
            return result;
        }
        FuzzyQueryDaoDto daoDto = new FuzzyQueryDaoDto();
        daoDto.setKeyWord(daoDto.getKeyWord());
        daoDto.setPageSize(pageSize);
        daoDto.setPageNum(pageNum);
        List<CarCustomerModel> list = carCustomerDao.fuzzyQueryCarCustomerByKeyWord(keyWord);
        if (null != list && list.size() > 0) {
            total += list.size();
            Integer startIndex = pageSize * (pageNum - 1);
            Integer endIndex = startIndex + pageSize * pageNum;
            list = list.stream().skip(startIndex).limit(endIndex).collect(Collectors.toList());
        }
        FuzzyQueryVo vo = new FuzzyQueryVo();
        vo.setTotal(total);
        vo.setPageSize(pageSize);
        vo.setPageNum(pageNum);
        vo.setLastPage(total / pageSize + 1);
        vo.setResultList(list);
        return JSONResult.ok(vo);
    }

    @Override
    public JSONResult fuzzyQueryCarByKeyWord(FuzzyQueryDto dto) {
        String keyWord = dto.getKeyWord();
        Integer pageSize = dto.getPageSize();
        Integer pageNum = dto.getPageNum();
        Integer total = 0;
        JSONResult result = this.checkParam(dto);
        if (null != result) {
            return result;
        }
        FuzzyQueryDaoDto daoDto = new FuzzyQueryDaoDto();
        daoDto.setKeyWord(daoDto.getKeyWord());
        daoDto.setPageSize(pageSize);
        daoDto.setPageNum(pageNum);
        List<Car> list = carCustomerDao.fuzzyQueryCarByKeyWord(keyWord);
        if (null != list && list.size() > 0) {
            total += list.size();
            Integer startIndex = pageSize * (pageNum - 1);
            Integer endIndex = startIndex + pageSize * pageNum;
            list = list.stream().skip(startIndex).limit(endIndex).collect(Collectors.toList());
        }
        FuzzyQueryVo vo = new FuzzyQueryVo();
        vo.setTotal(total);
        vo.setPageSize(pageSize);
        vo.setPageNum(pageNum);
        vo.setLastPage(total / pageSize + 1);
        vo.setResultList(list);
        return JSONResult.ok(vo);
    }

    private JSONResult checkParam(FuzzyQueryDto dto) {
        Integer pageSize = dto.getPageSize();
        Integer pageNum = dto.getPageNum();
        if (null == pageSize) {
            return JSONResult.errorParamMsg("pageSize不能为空");
        } else if (pageSize <= 0) {
            return JSONResult.errorParamMsg("pageSize不能小于等于0");
        }
        if (null == pageNum) {
            return JSONResult.errorParamMsg("pageNum不能为空");
        } else if (pageNum <= 0) {
            return JSONResult.errorParamMsg("pageNum不能小于等于0");
        }
        return null;
    }
}
