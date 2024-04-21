package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuntimeInvisibleAnnotationsAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 6, 0, 1, 0, 6, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        // TODO constantPoolNode
        RuntimeInvisibleAnnotationsAttribute runtimeInvisibleAnnotationsAttribute = new RuntimeInvisibleAnnotationsAttribute(byteContainer, null, 1);
        int numAnnotations = runtimeInvisibleAnnotationsAttribute.getNumAnnotations();
        assertEquals(1, numAnnotations);
        assertEquals(numAnnotations, runtimeInvisibleAnnotationsAttribute.getAnnotations().length);

    }


}