package com.ming.core.orm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * jpa 操作类型
 *
 * @author ming
 * @date 2020-04-21 16:29:08
 */
@ApiModel("查询过滤器-操作类型")
public enum JpaOperator {
    /**
     * 精确比较
     */
    @ApiModelProperty("精确比较")
    EQ,
    /**
     * 模糊查询
     */
    @ApiModelProperty("模糊查询")
    LIKE,
    /**
     * 模糊查询取反
     */
    @ApiModelProperty("模糊查询取反")
    NOT_LIKE,
    /**
     * 大于
     */
    @ApiModelProperty("大于")
    GT,
    /**
     * 小于
     */
    @ApiModelProperty("小于")
    LT,
    /**
     * 大于等于
     */
    @ApiModelProperty("大于等于")
    GTE,
    /**
     * 小于等于
     */
    @ApiModelProperty("小于等于")
    LTE,
    /**
     * 在这部分参数中
     */
    @ApiModelProperty("在这部分参数中")
    IN,
    /**
     * 不等于
     */
    @ApiModelProperty("不等于")
    NEQ,
    /**
     * 或
     */
    @ApiModelProperty("或")
    OR;

}
