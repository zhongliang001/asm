package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassTableTest {

    byte[] bytes = new byte[]{0, 22, 0, 18, 0, 23, 0, 2};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        ClassTable classTable = new ClassTable(byteContainer);
        assertEquals(22, classTable.getInnerClassInfoIndex());
        assertEquals(18, classTable.getOuterClassInfoIndex());
        assertEquals(23, classTable.getInnerNameIndex());
        String[] strings = new String[]{"ACC_PRIVATE"};
        assertArrayEquals(strings, classTable.getInnerClassAccessFlags().getAccessFlags().toArray());
    }


}