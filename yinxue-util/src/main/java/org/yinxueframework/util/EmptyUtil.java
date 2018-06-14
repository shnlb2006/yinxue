package org.yinxueframework.util;

import java.util.*;

/**
 * 返回空对象，避免空指针 <br>
 * @author zengjian
 * @create 2018-06-06 15:07
 * @since 1.0.0
 */
public abstract class EmptyUtil {

    private static final List EMPTY_LIST = Collections.EMPTY_LIST;
    private static final Map EMPTY_MAP = Collections.EMPTY_MAP;
    private static final Set EMPTY_SET = Collections.EMPTY_SET;

    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final String[] EMPTY_STR_ARRAY = new String[0];
    private static final Object[] EMPTY_OBJ_ARRAY = new Object[0];

    private static final Object EMPTY_OBJECT = new Object();

    public static List getEmptyList(){
        return EMPTY_LIST;
    }

    public static Set getEmptySet(){
        return EMPTY_SET;
    }

    public static Map getEmptyMap(){
        return EMPTY_MAP;
    }

    public static int[] getEmptyIntArray(){
        return EMPTY_INT_ARRAY;
    }

    public static String[] getEmptyStrArray(){
        return EMPTY_STR_ARRAY;
    }

    public static Object[] getEmptyObjArray(){
        return EMPTY_OBJ_ARRAY;
    }

    public static Object getEmptyObject(){
        return EMPTY_OBJECT;
    }


}
