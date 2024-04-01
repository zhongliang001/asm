package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerConstantTest {

    byte[] bytes = new byte[]{0, 0, 0, 10};

    @Test
    public void Test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        IntegerConstant integerConstant = new IntegerConstant(byteContainer, ConstantKind.CONSTANT_Integer, 1);
        String value = integerConstant.getValue();
        assertEquals("10", value);
    }

}