package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumConstValueTest {

    byte[] bytes = new byte[]{0, 75, 0, 76};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        // TODO constantPoolNode
        EnumConstValue enumConstValue = new EnumConstValue(byteContainer, null);
        int typeNameIndex = enumConstValue.getTypeNameIndex();
        assertEquals(75, typeNameIndex);
        int consNameIndex = enumConstValue.getConsNameIndex();
        assertEquals(76, consNameIndex);


    }

}