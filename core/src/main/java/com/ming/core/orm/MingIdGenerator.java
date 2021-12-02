package com.ming.core.orm;

import com.ming.core.utils.IdFactory;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * 自定义id生成器
 *
 * @author ming
 * @date 2019-08-22 11:44:17
 */
@Getter
@ToString
public class MingIdGenerator implements IdentifierGenerator, Configurable {
    /**
     * 生成规则
     *
     * @author ming
     * @date 2019-08-22 11:44:25
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return IdFactory.newSnowFlakeId();
    }

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
    }
}
