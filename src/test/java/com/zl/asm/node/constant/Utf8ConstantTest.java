package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Utf8ConstantTest {

    static byte[] bytes = new byte[]{0, 13, 67, 111, 110, 115, 116, 97, 110, 116, 86, 97, 108, 117, 101};
    static ByteContainer byteContainer;
    static Utf8Constant utf8Constant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        utf8Constant = new Utf8Constant(byteContainer, ConstantKind.CONSTANT_Utf8, 1);
    }

    @Test
    public void utf8Constant() {
        String value = utf8Constant.getValue();
        assertEquals("ConstantValue", value);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        utf8Constant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}