package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.ann.Annotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterAnnotationTest {

    byte[] bytes = new byte[]{0, 1, 0, 44, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        ParameterAnnotation parameterAnnotation = new ParameterAnnotation(byteContainer);
        int numAnnotations = parameterAnnotation.getNumAnnotations();
        assertEquals(1, numAnnotations);
        Annotation[] annotations = parameterAnnotation.getAnnotations();
        assertEquals(1, annotations.length);

    }

}