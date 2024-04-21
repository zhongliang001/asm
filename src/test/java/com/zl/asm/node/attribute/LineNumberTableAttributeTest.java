package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineNumberTableAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 10, 0, 2, 0, 0, 0, 3, 0, 4, 0, 6};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute(byteContainer, 1);
        int lineNumberTableLength = lineNumberTableAttribute.getLineNumberTableLength();
        assertEquals(2, lineNumberTableLength);
        LineNumberTable[] lineNumberTables = lineNumberTableAttribute.getLineNumberTables();
        assertEquals(2, lineNumberTables.length);
    }

}