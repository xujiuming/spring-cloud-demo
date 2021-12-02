package com.ming.core.orm;


/**
 * jpa 操作类型
 *
 * @author ming
 * @date 2020-04-21 16:29:08
 */
public enum JpaOperator {
    /**
     * 精确比较
     */
    EQ,
    /**
     * 模糊查询
     */
    LIKE,
    /**
     * 模糊查询取反
     */
    NOT_LIKE,
    /**
     * 大于
     */
    GT,
    /**
     * 小于
     */
    LT,
    /**
     * 大于等于
     */
    GTE,
    /**
     * 小于等于
     */
    LTE,
    /**
     * 在这部分参数中
     */
    IN,
    /**
     * 不等于
     */
    NEQ,
    /**
     * 或
     */
    OR;

}
