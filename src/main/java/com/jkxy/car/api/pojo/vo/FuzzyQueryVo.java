package com.jkxy.car.api.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jkxy.car.api.pojo.model.CarCustomerModel;
import lombok.Data;

import java.util.List;

/**
 * @author spxxkw
 * @date 2022/09/02
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuzzyQueryVo {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("pageNum")
    private Integer pageNum;

    @JsonProperty("lastPage")
    private Integer lastPage;

    @JsonProperty("resultList")
    private List<?> resultList;

}
