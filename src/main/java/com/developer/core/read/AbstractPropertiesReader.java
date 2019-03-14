package com.developer.core.read;

import com.developer.core.CoreException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public abstract class AbstractPropertiesReader implements Reader<Properties> {

    @Override
    public Properties read(String filePath) {
        if (filePath == null || !filePath.endsWith(".properties")) {
            throw new CoreException("End with must be .properties");
        }
        try {
            Properties properties = new Properties();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            properties.load(bufferedReader);
            return properties;
        } catch (IOException e) {
            throw new CoreException("IO Exception");
        }
    }
}
