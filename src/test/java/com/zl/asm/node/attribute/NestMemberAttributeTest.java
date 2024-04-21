package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NestMemberAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 4, 0, 1, 0, 22};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        //  TODO constantPoolNode
        NestMemberAttribute nestMemberAttribute = new NestMemberAttribute(byteContainer, null, 1);
        int numberOfClasses = nestMemberAttribute.getNumberOfClasses();
        assertEquals(1, numberOfClasses);
        int[] classes = nestMemberAttribute.getClasses();
        assertEquals(22, classes[0]);
    }

}