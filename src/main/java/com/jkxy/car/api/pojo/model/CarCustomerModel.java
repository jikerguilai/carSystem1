package com.jkxy.car.api.pojo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author spxxkw
 * @date 2022/08/30
 */
@Data
public class CarCustomerModel {
    private Integer id;
    private String carSeries;
    private String carName;
    private String addCarTime;
    private String buyCarTime;
    private String customer;
    private String sold; // 0：未售出；1：已售出
}
