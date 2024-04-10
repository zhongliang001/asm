package com.zl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public abstract class HelloWorld implements Cloneable, TestIn {
    private static final int intval = 10;

    private final int intval2 = 10;

    public void test() throws IOException {
        int a = 1;
        int b = 2;
        int c = a + b;
        URL resource = this.getClass().getClassLoader().getResource("./");
        if (resource != null) {
            try (FileInputStream fileInputStream = new FileInputStream(new File(resource.getPath()));) {
                byte[] bytes = fileInputStream.readAllBytes();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
