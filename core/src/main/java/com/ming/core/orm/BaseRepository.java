package com.ming.core.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 自定义 基础 repository
 *
 * @author ming
 * @date 2019-09-23 15:46:43
 */
@NoRepositoryBean
public interface BaseRepository<T extends OrmSuperClass, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {


    /**
     * 逻辑删除
     *
     * @param id 数据id
     * @author ming
     * @date 2020-04-13 17:48:23
     */
    @Query(value = "update #{#entityName} set deleted = true where id = ?1 ")
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    void logicDeleteById(ID id);
}
