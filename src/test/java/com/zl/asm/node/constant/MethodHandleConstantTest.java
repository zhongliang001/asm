package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodHandleConstantTest {

    byte[] bytes = new byte[]{6, 0, 118};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        MethodHandleConstant constant = new MethodHandleConstant(byteContainer, ConstantKind.CONSTANT_MethodHandle_info, 1);
        int referenceKind = constant.getReferenceKind();
        int referenceIndex = constant.getReferenceIndex();
        assertEquals(6, referenceKind);
        assertEquals(118, referenceIndex);
    }

}