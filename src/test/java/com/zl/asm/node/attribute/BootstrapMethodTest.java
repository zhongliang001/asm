package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BootstrapMethodTest {

    byte[] bytes = new byte[]{0, 89, 0, 3, 0, 90, 0, 91, 0, 92};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        // TODO constantPoolNode
        BootstrapMethod bootstrapMethod = new BootstrapMethod(byteContainer, null);
        int[] bootstrapArguments = bootstrapMethod.getBootstrapArguments();
        int[] bootstrapArgumentsTarget = new int[]{90, 91, 92};
        assertArrayEquals(bootstrapArgumentsTarget, bootstrapArguments);
        int bootstrapMethodRef = bootstrapMethod.getBootstrapMethodRef();
        assertEquals(89, bootstrapMethodRef);
        int numBootstrapArguments = bootstrapMethod.getNumBootstrapArguments();
        assertEquals(3, numBootstrapArguments);
    }

}