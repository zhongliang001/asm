package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongConstantTest {

    byte[] bytes = new byte[]{0, 0, 0, 0, -1, -1, -1, -1};

    @Test
    public void test() {
        ByteContainer bc = new ByteContainer(bytes);
        LongConstant longConstant = new LongConstant(bc, ConstantKind.CONSTANT_Long, 1);
        long highBytes = longConstant.getHighBytes();
        long lowBytes = longConstant.getLowBytes();
        assertEquals(0L, highBytes);
        assertEquals(4294967295L, lowBytes);
    }

}