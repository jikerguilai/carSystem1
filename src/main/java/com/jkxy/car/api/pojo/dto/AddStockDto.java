package com.jkxy.car.api.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author spxxkw
 * @date 2022/08/30
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddStockDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("addStock")
    private Integer addStock;

}
