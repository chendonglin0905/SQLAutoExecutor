package com.developer.core.read;

import com.developer.core.CoreException;

import java.net.URL;
import java.util.Properties;

/**
 * <p>
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public class JDBCPropertiesReader extends AbstractPropertiesReader implements ClasspathSourceRead<Properties> {

    @Override
    public Properties read() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("jdbc.properties");
        if (url == null) {
            throw new CoreException("jdbc.properties file not found");
        }
        String path = url.getPath();
        return read(path);
    }
}
