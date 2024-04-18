package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerConstantTest {

    static byte[] bytes = new byte[]{0, 0, 0, 10};
    static IntegerConstant integerConstant;

    @BeforeAll
    public static void init() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        integerConstant = new IntegerConstant(byteContainer, ConstantKind.CONSTANT_Integer, 1);
    }

    @Test
    public void Test() {
        String value = integerConstant.getValue();
        assertEquals("10", value);
    }

    @Test
    void getLog() {
        StringBuilder builder = new StringBuilder();
        integerConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}