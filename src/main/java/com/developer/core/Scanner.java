package com.developer.core;

import java.io.File;
import java.util.List;

/**
 * <p>扫描器
 * <b>创建日期：</b> 2019/3/14
 * </p>
 *
 * @author chendonglin
 * @since 1.0.0-SNAPSHOT
 */
public interface Scanner {

    /**
     * 扫描某个路径下面的所有文件
     *
     * @param rootPath 根路径
     */
    void scan(String rootPath);

    /**
     * 扫描某个目录下的所有文件
     *
     * @param file 扫描文件
     */
    void scan(File file);

    /**
     * 获取扫描的文件
     *
     * @return 文件列表
     */
    List<File> getScanFiles();

}
