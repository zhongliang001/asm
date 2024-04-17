package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringConstantTest {

    byte[] bytes = new byte[]{0, 31};

    @Test
    public void test() {
        ByteContainer bc = new ByteContainer(bytes);
        StringConstant stringConstant = new StringConstant(bc, ConstantKind.CONSTANT_String, 1);
        int stringIndex = stringConstant.getStringIndex();
        assertEquals(31, stringIndex);
    }

}