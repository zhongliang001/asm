package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleConstantTest {

    static byte[] bytes = new byte[]{-65, -16, 0, 0, 0, 0, 0, 0};

    static ByteContainer byteContainer;
    static DoubleConstant doubleConstant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        doubleConstant = new DoubleConstant(byteContainer, ConstantKind.CONSTANT_Double, 1);
    }

    @Test
    public void test() {
        double v = doubleConstant.getdValue();
        assertEquals(-1, v);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        doubleConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }

}