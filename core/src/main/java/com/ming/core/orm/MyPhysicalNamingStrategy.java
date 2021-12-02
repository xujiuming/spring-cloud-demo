package com.ming.core.orm;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * 自定义jpa 名称映射
 *
 * @author ming
 * @date 2021-08-26 16:25:13
 */
public class MyPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    /**
     * 表名映射
     * xxxEntity -> t_xxx
     *
     * @author ming
     * @date 2021-08-26 16:25:24
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier = super.toPhysicalTableName(Identifier.toIdentifier(name.getText().replace("Entity", ""), name.isQuoted()), jdbcEnvironment);
        return Identifier.toIdentifier("t_" + identifier.getText(), identifier.isQuoted());
    }
}
