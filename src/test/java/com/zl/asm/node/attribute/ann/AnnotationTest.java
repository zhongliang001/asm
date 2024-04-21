package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationTest {

    byte[] bytes = new byte[]{0, 21, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        // TODO constantPoolNode
        Annotation annotation = new Annotation(byteContainer, null);
        int typeIndex = annotation.getTypeIndex();
        assertEquals(21, typeIndex);
    }

}