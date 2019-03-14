package com.developer.core.read;

/**
 * <p>读取classpath 目录下的文件
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public interface ClasspathSourceReader<T> {

    T read();
}
