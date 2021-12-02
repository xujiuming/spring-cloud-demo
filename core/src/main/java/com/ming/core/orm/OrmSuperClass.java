package com.ming.core.orm;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;

/**
 * 继承映射父类  所有entity 继承这个类
 * jackson 转换long的时候 会丢精度 这里采用转换为string 处理 或者使用BigInteger
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class OrmSuperClass implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "mingIdGenerator")
    @GenericGenerator(name = "mingIdGenerator", strategy = "com.ming.core.orm.MingIdGenerator",
            parameters = {@Parameter(name = "prefix", value = "inid")})
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private long createTimeMillis;
    private long lastUpdateTimeMillis = System.currentTimeMillis();
}
