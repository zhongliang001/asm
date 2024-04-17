package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodParametersAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 5, 1, 0, 73, 0, 0};

    @Test
    public void test() {
        ByteContainer bc = new ByteContainer(bytes);
        MethodParametersAttribute attribute = new MethodParametersAttribute(bc, 1);
        int parametersCount = attribute.getParametersCount();
        assertEquals(1, parametersCount);
        assertEquals(parametersCount, attribute.getParameters().length);
    }

}