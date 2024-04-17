package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeArgumentTargetTest {

    byte[] bytes = new byte[]{0, 85, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        TypeArgumentTarget target = new TypeArgumentTarget(byteContainer);
        int offset = target.getOffset();
        assertEquals(85, offset);
        int typeArgumentIndex = target.getTypeArgumentIndex();
        assertEquals(0, typeArgumentIndex);

    }

}