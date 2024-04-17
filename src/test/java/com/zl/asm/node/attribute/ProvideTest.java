package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProvideTest {

    byte[] bytes = new byte[]{};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        Provide provide = new Provide(byteContainer);
        int providesIndex = provide.getProvidesIndex();
        assertEquals(1, providesIndex);
        int providesWithCount = provide.getProvidesWithCount();
        assertEquals(1, providesWithCount);
        int[] providesWithIndex = provide.getProvidesWithIndex();
        int[] target = new int[]{};
        assertArrayEquals(target, providesWithIndex);

    }

}