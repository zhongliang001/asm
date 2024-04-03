package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineNumbeTableTest {

    byte[] bytes = new byte[]{0, 4, 0, 6};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        LineNumberTable lineNumberTable = new LineNumberTable(byteContainer);
        int startPc = lineNumberTable.getStartPc();
        assertEquals(4, startPc);
        int lineNumber = lineNumberTable.getLineNumber();
        assertEquals(6, lineNumber);
    }

}