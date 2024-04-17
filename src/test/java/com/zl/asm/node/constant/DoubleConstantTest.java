package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleConstantTest {

    byte[] bytes = new byte[]{-65, -16, 0, 0, 0, 0, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        DoubleConstant doubleConstant = new DoubleConstant(byteContainer, ConstantKind.CONSTANT_Double, 1);
        double v = doubleConstant.getdValue();
        assertEquals(-1, v);
    }

}