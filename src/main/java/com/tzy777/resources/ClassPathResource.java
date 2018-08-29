package com.tzy777.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 16:01
 */
public class ClassPathResource implements Resource{

    private final String path;

    private final File file;

    public ClassPathResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public boolean isExist() {
        return file.exists();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.getClass().getClassLoader().getResourceAsStream(path);
    }
}
