package com.tzy777.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 16:00
 */
public interface Resource {
    File getFile();

    boolean isExist();

    InputStream getInputStream() throws IOException;
}
