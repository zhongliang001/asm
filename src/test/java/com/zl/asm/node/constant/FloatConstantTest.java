package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloatConstantTest {

    byte[] bytes = new byte[]{63, 64, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        FloatConstant constant = new FloatConstant(byteContainer, ConstantKind.CONSTANT_Float, 1);
        float v = constant.getfValue();
        assertEquals(0.75f, v);
    }


}