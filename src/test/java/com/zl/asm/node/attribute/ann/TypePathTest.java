package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypePathTest {

    byte[] bytes = new byte[]{1, 3, 0};

    @Test
    public void test() {
        ByteContainer b = new ByteContainer(bytes);
        TypePath path = new TypePath(b);
        int pathLength = path.getPathLength();
        assertEquals(1, pathLength);
        assertEquals(pathLength, path.getPathTables().length);
    }

}