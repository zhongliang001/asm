package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeParameterBoundTargetTest {

    byte[] bytes = new byte[]{1, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        TypeParameterBoundTarget target = new TypeParameterBoundTarget(byteContainer);
        int typeParameterIndex = target.getTypeParameterIndex();
        assertEquals(1, typeParameterIndex);
        int boundIndex = target.getBoundIndex();
        assertEquals(0, boundIndex);
    }

}