package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloatConstantTest {

    static byte[] bytes = new byte[]{63, 64, 0, 0};
    static ByteContainer byteContainer;
    static FloatConstant constant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        constant = new FloatConstant(byteContainer, ConstantKind.CONSTANT_Float, 1);
    }

    @Test
    public void test() {
        float v = constant.getfValue();
        assertEquals(0.75f, v);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        constant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }

}