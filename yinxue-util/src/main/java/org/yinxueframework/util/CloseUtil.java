package org.yinxueframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 资源释放类Util <br>
 * @author zengjian
 * @create 2018-06-06 14:26
 * @since 1.0.0
 */
public abstract class CloseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloseUtil.class);

    public static void close(AutoCloseable... args) {
        if (args == null) {
            return;
        }
        for (AutoCloseable arg : args) {
            if (arg != null) {
                try {
                    arg.close();
                } catch (Exception e) {
                    LOGGER.error("close failed", e);
                }
                arg = null;
            }
        }
    }

}
