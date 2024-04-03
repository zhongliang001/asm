package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 2, 0, 10};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        ConstantAttribute constantAttribute = new ConstantAttribute(byteContainer, 1);
        int constantvalueIndex = constantAttribute.getConstantvalueIndex();
        assertEquals(10, constantvalueIndex);
    }

}