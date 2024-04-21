package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleConstantTest {
    static byte[] bytes = new byte[]{0, 20};

    static ByteContainer byteContainer;
    static ModuleConstant moduleConstant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        //TODO constantPoolNode
        moduleConstant = new ModuleConstant(byteContainer, null, ConstantKind.CONSTANT_Module_info, 1);
    }

    @Test
    public void test() {
        int nameIndex = moduleConstant.getNameIndex();
        assertEquals(20, nameIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        moduleConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}