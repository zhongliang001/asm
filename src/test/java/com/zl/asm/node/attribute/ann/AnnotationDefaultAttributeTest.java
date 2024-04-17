package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationDefaultAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 3, 73, 0, 16};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        AnnotationDefaultAttribute attribute = new AnnotationDefaultAttribute(byteContainer, 1);
        ElementValue elementValue = attribute.getElementValue();
        char tag = elementValue.getTag();
        assertEquals('I', tag);
    }

}