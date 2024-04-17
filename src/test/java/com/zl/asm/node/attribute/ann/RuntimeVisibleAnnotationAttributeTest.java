package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuntimeVisibleAnnotationAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 6, 0, 1, 0, 21, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        RuntimeVisibleAnnotationAttribute attribute = new RuntimeVisibleAnnotationAttribute(byteContainer, 1);
        int numAnnotations = attribute.getNumAnnotations();
        assertEquals(1, numAnnotations);
        Annotation[] annotations = attribute.getAnnotations();
        assertEquals(1, annotations.length);

    }

}