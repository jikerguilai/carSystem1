package com.jkxy.car.api.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author spxxkw
 * @date 2022/09/01
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuzzyQueryDto {

    @JsonProperty("keyWord")
    private String keyWord;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("pageNum")
    private Integer pageNum;

}
