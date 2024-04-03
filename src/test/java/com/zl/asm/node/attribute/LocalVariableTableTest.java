package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalVariableTableTest {
    byte[] bytes = new byte[]{0, 0, 0, 11, 0, 17, 0, 18, 0, 0};

    @Test
    public void test(){
        ByteContainer byteContainer = new ByteContainer(bytes);
        LocalVariableTable localVariableTable = new LocalVariableTable(byteContainer);
        int nameIndex = localVariableTable.getNameIndex();
        assertEquals(17,nameIndex);
        int descriptorIndex = localVariableTable.getDescriptorIndex();
        assertEquals(18,descriptorIndex);
    }
}