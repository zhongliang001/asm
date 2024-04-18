package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringConstantTest {

    static byte[] bytes = new byte[]{0, 31};

    static ByteContainer bc;
    static StringConstant stringConstant;

    @BeforeAll
    public static void init() {
        bc = new ByteContainer(bytes);
        stringConstant = new StringConstant(bc, ConstantKind.CONSTANT_String, 1);
    }

    @Test
    public void test() {
        int stringIndex = stringConstant.getStringIndex();
        assertEquals(31, stringIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        stringConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}