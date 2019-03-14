package com.developer.core.sql;

import com.developer.core.CoreException;
import com.developer.core.Executor;
import com.developer.core.Scanner;
import com.sun.deploy.util.OrderedHashSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>SQL自动执行器，读取classpath下面，所有.sql结尾的文件，并执行文件
 * 中的SQL, 动建表，脚本自动执行等场景。
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public class SQLAutoExecutor implements Executor, Scanner {

    private String rootPath;

    private List<File> sqlFiles = new ArrayList<>();

    private String sql;

    private ConnectionTool tool;

    public SQLAutoExecutor() {
        //从当前线程下面加载classLoader
        this.rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        tool = new SimpleConnectionTool();
    }

    public SQLAutoExecutor(String rootPath) {
        this.rootPath = rootPath;
        tool = new SimpleConnectionTool();
    }

    public void scan(String rootPath) {
        this.scan(new File(rootPath));
    }

    public void scan(File file) {
        boolean isDir = file.isDirectory();
        if (isDir) {
            File[] children = file.listFiles();
            if (children == null || children.length == 0) {
                return;
            }
            for (File child : children) {
                this.scan(child);
            }
        } else {
            String fileName = file.getName();
            if (fileName.endsWith(".sql") || fileName.endsWith(".SQL")) {
                sqlFiles.add(file);
            }
        }
    }

    public List<File> getScanFiles() {
        return sqlFiles;
    }

    public void execute() {
        if (rootPath == null) {
            throw new RuntimeException("Root path is null , so can not scan");
        }
        this.scan(rootPath);
        List<File> sqlFiles = this.getScanFiles();
        if (sqlFiles == null || sqlFiles.size() == 0) {
            return;
        }
        // Sql Appender
        StringBuilder builder = new StringBuilder();
        // All SQL files
        for (File sqlFile : sqlFiles) {
            try {
                FileInputStream inputStream = new FileInputStream(sqlFile);

                byte[] readBytes = new byte[1024];

                while (inputStream.read(readBytes) != -1) {
                    // append sql
                    builder.append(new String(readBytes));
                }

            } catch (FileNotFoundException e) {
                throw new CoreException("File Not Fount exception");
            } catch (IOException e) {
                throw new CoreException("Read file failed");
            }
        }
        this.sql = builder.toString();
        // Run sql
        this.runSQL(sql);
    }

    @Override
    public void runSQL(String sql) {
        if (sql == null || "".equalsIgnoreCase(sql)) {
            return;
        }
        Connection connection = tool.getConnection();
        try {
            // Begin transaction
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            // Commit
            connection.commit();
        } catch (SQLException e) {
            // Rollback
            try {
                connection.rollback();
                throw new CoreException("SQL execute error");
            } catch (SQLException e1) {
                throw new CoreException("Connection rollback failed");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

}
