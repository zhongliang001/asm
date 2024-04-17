package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalVarTableTest {

    byte[] bytes = new byte[]{0, 86, 0, 10, 0, 3};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        LocalVarTable localVarTable = new LocalVarTable(byteContainer);
        int startPc = localVarTable.getStartPc();
        assertEquals(86, startPc);
        int length = localVarTable.getLength();
        assertEquals(10, length);
        int index = localVarTable.getIndex();
        assertEquals(3, index);
    }

}