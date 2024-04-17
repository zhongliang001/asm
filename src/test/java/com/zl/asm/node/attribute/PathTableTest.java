package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathTableTest {
    byte[] bytes = new byte[]{3, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        PathTable pathTable = new PathTable(byteContainer);
        int typePathKind = pathTable.getTypePathKind();
        assertEquals(3, typePathKind);
        int typeArgumentIndex = pathTable.getTypeArgumentIndex();
        assertEquals(0, typeArgumentIndex);
    }

}