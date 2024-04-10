package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.stack.StackMapFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackMapTableAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 28, 0, 20, -3, 0, 92, 7, 0, 53, 1, 15, 15, 15, 15, 15, 15, 16, 16, 13, 51, 9, 10, 9, 9, 9, 9, 9, 9, 9};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute(byteContainer, 1);
        int numberOfEntries = stackMapTableAttribute.getNumberOfEntries();
        assertEquals(20, numberOfEntries);
        StackMapFrame[] stackMapFrames = stackMapTableAttribute.getStackMapFrames();
        assertEquals(20, stackMapFrames.length);
    }

}