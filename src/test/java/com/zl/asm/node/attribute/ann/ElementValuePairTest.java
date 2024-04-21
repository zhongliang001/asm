package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementValuePairTest {

    byte[] bytes = new byte[]{0, 52, 115, 0, 48};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        // TODO constantPoolNode
        ElementValuePair elementValuePair = new ElementValuePair(byteContainer, null);
        int elementNameIndex = elementValuePair.getElementNameIndex();
        assertEquals(52, elementNameIndex);
        ElementValue value = elementValuePair.getValue();
        char tag = value.getTag();
        assertEquals('s', tag);

    }

}