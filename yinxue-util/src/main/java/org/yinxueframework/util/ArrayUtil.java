package org.yinxueframework.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 数组工具类
 *
 * @author zengjian
 * @create 2018-06-06 17:48
 * @since 1.0.0
 */
public abstract class ArrayUtil {

    public static <T> List<T> convertToList(T[] array){
        ValidateUtil.notNull(array);
        List<T> result = new ArrayList<>(array.length);
        for (T t : array) {
            result.add(t);
        }
        return result;
    }

    public static <T> T[] convertToArray(List<T> list){
        ValidateUtil.notEmpty(list);
        return list.toArray((T[]) Array.newInstance(list.get(0).getClass(), list.size()));
    }
}
