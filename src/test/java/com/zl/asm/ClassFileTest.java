package com.zl.asm;

import com.zl.asm.node.ClassFile;
import com.zl.asm.reader.ClassStandardReader;
import org.junit.jupiter.api.Test;

class ClassFileTest {

    @Test
    public void testConstruct() {
        String relatepath = "./com/zl/HelloWorld.class";
        ClassFile classFile = new ClassFile(relatepath);
        ClassStandardReader classStandardReader = new ClassStandardReader();
        classFile.accept(classStandardReader);
        classStandardReader.log();
    }

}