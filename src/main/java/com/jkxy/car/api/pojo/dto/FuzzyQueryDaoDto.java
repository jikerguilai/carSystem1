package com.jkxy.car.api.pojo.dto;

import lombok.Data;

/**
 * @author spxxkw
 * @date 2022/09/02
 */

@Data
public class FuzzyQueryDaoDto {
    private String keyWord;
    private Integer pageSize;
    private Integer pageNum;
}
