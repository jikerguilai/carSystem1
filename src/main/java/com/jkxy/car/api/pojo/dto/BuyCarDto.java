package com.jkxy.car.api.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author spxxkw
 * @date 2022/08/30
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyCarDto {

    // 顾客姓名
    @JsonProperty("customer")
    private String customer;

    // 车系
    @JsonProperty("carSeries")
    private String carSeries;

    // 购车数量
    @JsonProperty("count")
    private Integer count;

}
