package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TypeAnnotationTest {

    byte[] bytes = new byte[]{17, 0, 0, 0, 0, 62, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        //TODO constantPoolNode
        TypeAnnotation annotation = new TypeAnnotation(byteContainer, null);
        String targetType = annotation.getTargetType();
        assertEquals("11", targetType);
        TypeParameterBoundTarget typeParameterBoundTarget = annotation.getTypeParameterBoundTarget();
        assertNotNull(typeParameterBoundTarget);
    }

}