package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuntimeInvisibleTypeAnnAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 8, 0, 1, 19, 0, 0, 26, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        RuntimeInvisibleTypeAnnAttribute runtimeInvisibleTypeAnnAttribute = new RuntimeInvisibleTypeAnnAttribute(byteContainer, 1);
        int numAnnotations = runtimeInvisibleTypeAnnAttribute.getNumAnnotations();
        assertEquals(1, numAnnotations);
        assertEquals(numAnnotations, runtimeInvisibleTypeAnnAttribute.getTypeAnnotations().length);
    }

}