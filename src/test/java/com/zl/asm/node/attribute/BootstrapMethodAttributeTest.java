package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BootstrapMethodAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 12, 0, 1, 0, 89, 0, 3, 0, 90, 0, 91, 0, 92};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        BootstrapMethodAttribute bootstrapMethodAttribute = new BootstrapMethodAttribute(byteContainer, 1);
        int numBootstrapMethods = bootstrapMethodAttribute.getNumBootstrapMethods();
        assertEquals(1, numBootstrapMethods);
        BootstrapMethod[] bootstrapMethods = bootstrapMethodAttribute.getBootstrapMethods();
        BootstrapMethod bootstrapMethod = bootstrapMethods[0];
        int[] bootstrapArguments = bootstrapMethod.getBootstrapArguments();
        int[] bootstrapArgumentsTarget = new int[]{90, 91, 92};
        assertArrayEquals(bootstrapArgumentsTarget, bootstrapArguments);
        int bootstrapMethodRef = bootstrapMethod.getBootstrapMethodRef();
        assertEquals(89, bootstrapMethodRef);
        int numBootstrapArguments = bootstrapMethod.getNumBootstrapArguments();
        assertEquals(3, numBootstrapArguments);
    }

}