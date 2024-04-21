package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalVariableTableAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 12, 0, 1, 0, 0, 0, 11, 0, 17, 0, 18, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        // TODO
        LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute(byteContainer, null, 1);
        int localVariableTableLength = localVariableTableAttribute.getLocalVariableTableLength();
        assertEquals(1, localVariableTableLength);
        LocalVariableTable[] localVariableTables = localVariableTableAttribute.getLocalVariableTables();
        assertEquals(1, localVariableTables.length);
        LocalVariableTable localVariableTable = localVariableTables[0];
        int nameIndex = localVariableTable.getNameIndex();
        assertEquals(17, nameIndex);
        int descriptorIndex = localVariableTable.getDescriptorIndex();
        assertEquals(18, descriptorIndex);
    }

}