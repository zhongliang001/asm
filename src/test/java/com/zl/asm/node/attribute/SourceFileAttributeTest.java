package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceFileAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 2, 0, 24};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        SourceFileAttribute sourceFileAttribute = new SourceFileAttribute(byteContainer, 1);
        int sourceFileIndex = sourceFileAttribute.getSourcefileIndex();
        assertEquals(24, sourceFileIndex);

    }

}