package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodHandleConstantTest {

    static byte[] bytes = new byte[]{6, 0, 118};

    static ByteContainer byteContainer;
    static MethodHandleConstant constant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        constant = new MethodHandleConstant(byteContainer, ConstantKind.CONSTANT_MethodHandle_info, 1);
    }

    @Test
    public void test() {
        int referenceKind = constant.getReferenceKind();
        int referenceIndex = constant.getReferenceIndex();
        assertEquals(6, referenceKind);
        assertEquals(118, referenceIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        constant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");

    }

}