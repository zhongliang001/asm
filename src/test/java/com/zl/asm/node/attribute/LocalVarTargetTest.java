package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalVarTargetTest {

    byte[] bytes = new byte[]{0, 1, 0, 86, 0, 10, 0, 3};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        LocalVarTarget target = new LocalVarTarget(byteContainer);
        int tableLength = target.getTableLength();
        assertEquals(1, tableLength);
        assertEquals(tableLength, target.getLocalVarTables().length);
    }

}