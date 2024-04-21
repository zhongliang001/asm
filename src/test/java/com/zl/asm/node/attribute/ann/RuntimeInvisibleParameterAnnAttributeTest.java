package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuntimeInvisibleParameterAnnAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 12, 1, 0, 1, 0, 10, 0, 1, 0, 11, 115, 0, 12};

    @Test
    public void test() {
        ByteContainer b = new ByteContainer(bytes);
        //TODO constantPoolNode
        RuntimeInvisibleParameterAnnAttribute attribute = new RuntimeInvisibleParameterAnnAttribute(b, null, 1);
        int numParameters = attribute.getNumParameters();
        assertEquals(1, numParameters);
        assertEquals(numParameters, attribute.getParameterAnnotations().length);

    }

}