package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementValueTest {

    byte[] bytes = new byte[]{115, 0, 88};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        //TODO constantPoolNode
        ElementValue elementValue = new ElementValue(byteContainer, null);
        char tag = elementValue.getTag();
        assertEquals('s', tag);
    }

}