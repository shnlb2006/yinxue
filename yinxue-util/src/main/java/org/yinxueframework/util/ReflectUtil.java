package org.yinxueframework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.*;

/**
 * 反射工具类 <br>
 * @author zengjian
 * @create 2018-06-06 14:49
 * @since 1.0.0
 */
public abstract class ReflectUtil {

    /**
     * 取得实体类属性 <br>
     *
     * @param src
     * @param <Object>
     * @return
     * @see ArrayUtils
     */
    public static List<Field> getFields(Object src) {
        Class<?> clazz = src.getClass();
        return getFieldsByClass(clazz);
    }

    /**
     * 根据Class对象获得所有除了final之外的属性 <br>
     *
     * @param clzz
     * @return
     */
    public static List<Field> getFieldsByClass(Class<?> clazz) {
        List<Field> result = new LinkedList<>();
        for (Class<?> initClazz = clazz; initClazz != Object.class; initClazz = initClazz.getSuperclass()) {
            Field[] fields =initClazz.getDeclaredFields();
            result.addAll(ArrayUtil.convertToList(fields));
        }
        // 排除final修饰的属性
        doFilterFinalField(result);
        return result;
    }

    private static void doFilterFinalField(List<Field> list) {
        // 迭代器删除
        Iterator<Field> iterator = list.iterator();
        while (iterator.hasNext()){
            Field field = iterator.next();
            if (Modifier.isFinal(field.getModifiers())){
                iterator.remove();
            }
        }
    }

    private static List<Method> getMethodsByClass(Class<?> clazz){
        ValidateUtil.notNull(clazz);
        Method[] methods = clazz.getDeclaredMethods();
        return ArrayUtil.convertToList(methods);
    }

    /**
     * 检查该对象属性是否存在null值，或者String类型属性为空值"" <br>
     *
     * @param t
     * @param <T>
     * @return 存在null为false 不存在返回true
     */
    public static <T> boolean checkFieldNotNull(T t) {
        List<Field> fields = getFields(t);
        // 遍历所有的field值进行比较，String类型采用null和""检查
        for (Field field : fields) {
            Object obj = null;
            try {
                // 不设置会报IllegalAccessException异常
                field.setAccessible(true);
                obj = field.get(t);
                if (obj == null) {
                    return false;
                }
                if (String.class == field.getType()) {
                    String value = (String) obj;
                    if (value.trim().equals("")) {
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 从对象中获得特定名称的属性值，没有返回null <br>
     *
     * @param t
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T> Object getFieldValue(T t, String fieldName) {
        Field f = null;
        try {
            f = t.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将map集合中的值赋值给实体类 <br>
     * @param t
     * @param values
     * @param <T>
     * @throws IllegalAccessException
     */
    public static <T> void setFieldValues(T t, Map<String, Object> values) throws IllegalAccessException {
        List<Field> fields = getFields(t);
        for (Field field : fields) {
            String fieldName = field.getName().trim().toLowerCase();
            Class type = field.getType();
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            Object columnValue = values.get(fieldName);
            // 如果为Long类型则使用BigInteger包装对象
            if (columnValue instanceof Long) {
                columnValue = new BigInteger(String.valueOf(columnValue));
            }
            field.set(t, columnValue);
        }
    }
}
