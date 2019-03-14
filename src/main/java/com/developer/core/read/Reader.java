package com.developer.core.read;

/**
 * <p> 资源读取
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public interface Reader<T> {

    /**
     * 资源读取
     *
     * @param filePath 文件路径
     * @return 解析对象
     */
    T read(String filePath);
}
