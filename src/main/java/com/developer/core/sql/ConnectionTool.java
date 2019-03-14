package com.developer.core.sql;

import java.sql.Connection;

/**
 * <p> 连接工具
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public interface ConnectionTool {

    Connection getConnection();

}
