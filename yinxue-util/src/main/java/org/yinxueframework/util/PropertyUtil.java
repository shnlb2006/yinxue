package org.yinxueframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import static org.yinxueframework.util.CloseUtil.*;

/**
 * 属性文件操作类 <br>
 *
 * @author zengjian
 * @create 2018-03-14 11:54
 * @since 1.0.0
 */
public final class PropertyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

    /**
     * classpath下properties资源路径
     */
    private String path;
    private Properties properties = new Properties();
    private long lastModified;
    private ClassLoader classLoader;

    /**
     * 构造器
     * @param path classpath下相对路径字符串
     */
    public PropertyUtil(String path) {
        this.path = path;
        this.classLoader = Thread.currentThread().getContextClassLoader();
        this.lastModified = getLastModified();
        loadProperites();
    }

    private long getLastModified() {
        return new File(getAbsolutePath()).lastModified();
    }

    private String getAbsolutePath() {
        return classLoader.getResource(this.path).getPath();
    }

    private void loadProperites() {
        InputStream is = classLoader.getResourceAsStream(path);
        try {
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(is);
        }
    }

    public synchronized String getProperty(String key) {
        // 如果文件修改时间发生变化，重新读取
        if (this.lastModified != getLastModified()) {
            loadProperites();
        }
        return properties.getProperty(key);
    }

    public synchronized void setProperty(String key, String value) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(getAbsolutePath());
            properties.setProperty(key, value);
            properties.store(fos, null);
        } catch (Exception e) {
            LOGGER.error("update property exception", e);
        } finally {
            close(fos);
        }
    }
}
