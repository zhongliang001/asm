package com.zl.asm.reader;

import com.zl.asm.node.ClassFile;
import org.junit.jupiter.api.Test;

import java.net.URL;

class FormatterReaderTest {

    @Test
    public void Test() {
        long start = System.currentTimeMillis();
        URL resource = this.getClass().getClassLoader().getResource("./com/zl/HelloWorld.class");
        if (resource != null) {
            String relatepath = resource.getPath();
            ClassFile classFile = new ClassFile(relatepath);
            FormatterReader reader = new FormatterReader();
            classFile.accept(reader);
            reader.log();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }

}