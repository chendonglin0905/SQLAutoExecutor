package com.developer.core.sql;

import com.developer.core.ConstantKeys;
import com.developer.core.CoreException;
import com.developer.core.read.ClasspathSourceRead;
import com.developer.core.read.JDBCPropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p> 简单的连接工具
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public class SimpleConnectionTool implements ConnectionTool {

    private ClasspathSourceRead<Properties> reader;

    public SimpleConnectionTool() {
        reader = new JDBCPropertiesReader();
    }

    @Override
    public Connection getConnection() {
        Properties properties = reader.read();
        String url = properties.getProperty(ConstantKeys.JDBC_URL);
        String username = properties.getProperty(ConstantKeys.JDBC_USERNAME);
        String password = properties.getProperty(ConstantKeys.JDBC_PASSWORD);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new CoreException("Database connect failed");
        }
    }
}
