package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayValueTest {

    byte[] bytes = new byte[]{0, 1, 99, 0, 47};

    @Test
    public void test() {
        ByteContainer bc = new ByteContainer(bytes);
        ArrayValue arrayValue = new ArrayValue(bc);
        int numValues = arrayValue.getNumValues();
        assertEquals(1, numValues);
        ElementValue[] elementValues = arrayValue.getElementValues();
        assertEquals(1, elementValues.length);

    }

}