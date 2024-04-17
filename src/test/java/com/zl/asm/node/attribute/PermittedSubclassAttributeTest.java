package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PermittedSubclassAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 6, 0, 2, 0, 63, 0, 67, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        PermittedSubclassAttribute attribute = new PermittedSubclassAttribute(byteContainer, 1);
        int[] classes = attribute.getClasses();
        int numberOfClasses = attribute.getNumberOfClasses();
        assertEquals(2, numberOfClasses);
        int[] target = new int[]{63, 67};
        assertArrayEquals(target, classes);


    }


}