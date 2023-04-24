package com.ddg.meituan.base.annotation.vaildator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: DDG
 * @Date: 2020/5/10 13:37
 * @Description: 接口中两个泛型 1 就是校验注解 2 就是标注什么字段上
 */
public class ListValueValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set;

    /**
     * 初始化方法：
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        // 将获取到的数据放到set中
        set = Arrays.stream(values).boxed().collect(Collectors.toSet());

    }

    /**
     * 判断是否是校验成功：value 指定的是需要进行校验的值 context校验的上下文环境
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return set.contains(value);
    }
}
