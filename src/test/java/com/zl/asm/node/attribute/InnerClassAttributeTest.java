package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InnerClassAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 10, 0, 1, 0, 22, 0, 18, 0, 23, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        //TODO constantPoolNode
        InnerClassAttribute innerClassAttribute = new InnerClassAttribute(byteContainer, null, 1);
        int numberOfClasses = innerClassAttribute.getNumberOfClasses();
        assertEquals(1, numberOfClasses);
        ClassTable[] classTables = innerClassAttribute.getClassTables();
        ClassTable classTable = classTables[0];
        assertEquals(22, classTable.getInnerClassInfoIndex());
        assertEquals(18, classTable.getOuterClassInfoIndex());
        assertEquals(23, classTable.getInnerNameIndex());
        assertEquals(0, classTable.getInnerClassAccessFlags().getAccessFlags().size());

    }

}