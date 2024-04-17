package com.zl.asm;

import com.zl.asm.node.ClassFile;
import com.zl.asm.reader.ClassStandardReader;
import org.junit.jupiter.api.Test;

class ClassFileTest {

    @Test
    public void testConstruct() {
        String relatepath = this.getClass().getClassLoader().getResource("./com/zl/HelloWorld.class").getPath();
        ClassFile classFile = new ClassFile(relatepath);
        ClassStandardReader classStandardReader = new ClassStandardReader();
        classFile.accept(classStandardReader);
        classStandardReader.log();
    }

}