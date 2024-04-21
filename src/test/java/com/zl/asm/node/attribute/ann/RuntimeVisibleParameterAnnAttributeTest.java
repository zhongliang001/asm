package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuntimeVisibleParameterAnnAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 12, 1, 0, 1, 0, 19, 0, 1, 0, 20, 115, 0, 21};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        //TODO constantPoolNode
        RuntimeVisibleParameterAnnAttribute attribute = new RuntimeVisibleParameterAnnAttribute(byteContainer, null, 1);
        int numParameters = attribute.getNumParameters();
        assertEquals(1, numParameters);
        assertEquals(numParameters, attribute.getParameterAnnotations().length);

    }

}