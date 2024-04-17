package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuntimeVisibleTypeAnnAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 10, 0, 1, 17, 0, 0, 0, 0, 62, 0, 0};

    @Test
    public void test() {
        ByteContainer container = new ByteContainer(bytes);
        RuntimeInvisibleAnnotationsAttribute runtimeInvisibleAnnotationsAttribute = new RuntimeInvisibleAnnotationsAttribute(container, 1);
        int numAnnotations = runtimeInvisibleAnnotationsAttribute.getNumAnnotations();
        assertEquals(1, numAnnotations);
    }

}