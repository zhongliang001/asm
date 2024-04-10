package com.zl;

import java.io.IOException;

public interface TestIn {
    void test() throws IOException;

    default void sayhi() {
        int a = 1;
    }
}
