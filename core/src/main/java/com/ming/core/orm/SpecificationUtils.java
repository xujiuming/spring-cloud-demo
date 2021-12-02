package com.ming.core.orm;

import com.google.common.collect.Lists;
import com.ming.core.CodeEnum;
import com.ming.core.CodeException;
import com.ming.core.orm.annotation.MyJpaSpecifications;
import com.ming.core.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 构建查询对Specification进行封装 用searchfilter enum进行封装
 *
 * @author ming
 * @date 2020-04-21 09:25:33
 */
@Slf4j
public class SpecificationUtils {

    /**
     * 根据class 获取字段 和字段的注解 和值  构建 条件
     *
     * @author ming
     * @date 2020-04-21 13:26:13
     */
    @SuppressWarnings({"unchecked", "AlibabaMethodTooLong"})
    public static <V> Specification<?> buildSpecificationByMyJpaSpecifications(V val) {
        Class<?> clazz = val.getClass();
        return (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            //当没有属性的时候 不构建规则
            if (CollectionUtils.isEmpty(clazz.getDeclaredFields())) {
                return builder.conjunction();
            }
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(Boolean.TRUE);
                //获取注解
                MyJpaSpecifications myJpaSpecifications = field.getAnnotation(MyJpaSpecifications.class);
                if (Objects.isNull(myJpaSpecifications)) {
                    //如果没有注解 忽略此字段 不进行构建处理
                    continue;
                }
                //如果没有输入实体字段 默认为当前属性字段的名称
                String nameStr = myJpaSpecifications.entityField();
                if (StringUtils.isEmpty(nameStr)) {
                    nameStr = field.getName();
                }
                //通过. 分割对象映射  如果存在多个对象组合  可以是用 xxx.xxx.xxx来获取path
                String[] names = StringUtils.split(nameStr, ".");
                Path expression = root.get(names[0]);
                for (int i = 1; i < names.length; i++) {
                    expression = expression.get(names[i]);
                }
                //in 和or 中需要的一个中间变量 用来将filter.value放入数组
                Object[] objects = new Object[1];
                //获取字段值
                Object value = ReflectionUtils.getField(field, val);
                //当 值为空的时候 不构建规则
                if (Objects.isNull(value)) {
                    continue;
                }
                switch (myJpaSpecifications.operator()) {
                    case EQ:
                        predicates.add(builder.equal(expression, value));
                        break;
                    case LIKE:
                        predicates.add(builder.like(expression, "%" + value + "%"));
                        break;
                    case NOT_LIKE:
                        predicates.add(builder.notLike(expression, "%" + value + "%"));
                        break;
                    case GT:
                        predicates.add(builder.greaterThan(expression, (Comparable) value));
                        break;
                    case LT:
                        predicates.add(builder.lessThan(expression, (Comparable) value));
                        break;
                    case GTE:
                        predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) value));
                        break;
                    case LTE:
                        predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) value));
                        break;
                    case IN:
                        //因为spring data jpa 本身没有对数组进行判断 传入数组的话会失败 所以在此进行是否是数组的判断
                        //因为expression。in参数是不定参数  理论上是可以传入数组 但是直接传入object不能判断是否为数组
                        //把他当成一个参数 而不是需要的数组参数
                        Object filterValue = value;
                        if (filterValue.getClass().isArray()) {
                            objects = (Object[]) filterValue;
                        } else {
                            objects[0] = filterValue;
                        }
                        predicates.add(expression.in(objects));
                        break;
                    case NEQ:
                        predicates.add(builder.notEqual(expression, value));
                        break;
                    case OR:
                        List<Predicate> preList = new ArrayList<>();
                        Object obj = value;
                        if (obj.getClass().isArray()) {
                            objects = (Object[]) obj;
                            for (Object object : objects) {
                                Predicate pp = builder.like(expression, "%" + object + "%");
                                preList.add(pp);
                            }
                        } else {
                            preList.add(builder.like(expression, "%" + obj + "%"));
                        }
                        Predicate[] pres = preList.toArray(new Predicate[0]);
                        predicates.add(builder.or(pres));
                        break;
                    default:
                        throw new CodeException(CodeEnum.DATA_NOT_FOUND, "没有" + myJpaSpecifications.operator().name() + "操作");
                }
            }
            if (CollectionUtils.notEmpty(predicates)) {
                return builder.and(predicates.toArray(new Predicate[0]));
            }
            return builder.conjunction();
        };
    }
}
