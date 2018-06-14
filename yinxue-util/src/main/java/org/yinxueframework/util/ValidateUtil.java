package org.yinxueframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 入参校验类Util <br>
 *
 * @author zengjian
 * @create 2018-06-06 14:28
 * @since 1.0.0
 */
public abstract class ValidateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateUtil.class);

    public static boolean isEmpty(final CharSequence cs) {
        return null == cs || 0 == cs.length() || "".equals(cs.toString().trim());
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(Collection col) {
        return null == col || 0 == col.size();
    }

    public static boolean isNotEmpty(Collection col) {
        return !isEmpty(col);
    }

    public static boolean isEmpty(Object[] args) {
        return null == args || 0 == args.length;
    }

    public static boolean isNotEmpty(Object[] args) {
        return !isEmpty(args);
    }

    public static void notEmpty(Object[] args) {
        if (null == args || 0 == args.length) {
            throw new IllegalArgumentException(args +" required not empty");
        }
    }

    public static void notNull(Object... args) {
        for (Object arg : args) {
            if (null == arg) {
                throw new IllegalArgumentException(arg + " required not null");
            }
        }
    }

    public static void notEmpty(Collection col) {
        if (isEmpty(col)) {
            throw new IllegalArgumentException("collection required not Empty");
        }
    }
}
