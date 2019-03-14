package com.developer.core;

/**
 * <p>
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public interface Executor {

    void execute();

    void runSQL(String sql);
}
