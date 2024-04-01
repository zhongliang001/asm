package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Utf8ConstantTest {

    byte[] bytes = new byte[]{0, 13, 67, 111, 110, 115, 116, 97, 110, 116, 86, 97, 108, 117, 101};

    @Test
    public void utf8Constant() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        Utf8Constant utf8Constant = new Utf8Constant(byteContainer, ConstantKind.CONSTANT_Utf8, 1);
        String value = utf8Constant.getValue();
        assertEquals("ConstantValue", value);
    }

}