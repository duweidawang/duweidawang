package com.example.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zn
 * @Version 1.0
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {}

    public static<T> T copyBean(Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    public static<T> List<T> copyBeanList(List<?> list, Class<T> clazz) {
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }

}
