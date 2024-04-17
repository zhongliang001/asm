package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SourceDebugExtensionAttributeTest {

    byte[] bytes = new byte[]{0, 0, 1, -83, 83, 77, 65, 80, 10, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 46, 107, 116, 10, 75, 111, 116, 108, 105, 110, 10, 42, 83, 32, 75, 111, 116, 108, 105, 110, 10, 42, 70, 10, 43, 32, 49, 32, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 46, 107, 116, 10, 111, 114, 103, 47, 115, 112, 114, 105, 110, 103, 102, 114, 97, 109, 101, 119, 111, 114, 107, 47, 97, 111, 116, 47, 104, 105, 110, 116, 47, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 75, 116, 10, 43, 32, 50, 32, 95, 65, 114, 114, 97, 121, 115, 46, 107, 116, 10, 107, 111, 116, 108, 105, 110, 47, 99, 111, 108, 108, 101, 99, 116, 105, 111, 110, 115, 47, 65, 114, 114, 97, 121, 115, 75, 116, 95, 95, 95, 65, 114, 114, 97, 121, 115, 75, 116, 10, 43, 32, 51, 32, 65, 114, 114, 97, 121, 115, 74, 86, 77, 46, 107, 116, 10, 107, 111, 116, 108, 105, 110, 47, 99, 111, 108, 108, 101, 99, 116, 105, 111, 110, 115, 47, 65, 114, 114, 97, 121, 115, 75, 116, 95, 95, 65, 114, 114, 97, 121, 115, 74, 86, 77, 75, 116, 10, 42, 76, 10, 49, 35, 49, 44, 50, 57, 58, 49, 10, 49, 49, 51, 51, 53, 35, 50, 58, 51, 48, 10, 49, 49, 54, 55, 48, 35, 50, 44, 51, 58, 51, 49, 10, 51, 55, 35, 51, 44, 50, 58, 51, 52, 10, 42, 83, 32, 75, 111, 116, 108, 105, 110, 68, 101, 98, 117, 103, 10, 42, 70, 10, 43, 32, 49, 32, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 46, 107, 116, 10, 111, 114, 103, 47, 115, 112, 114, 105, 110, 103, 102, 114, 97, 109, 101, 119, 111, 114, 107, 47, 97, 111, 116, 47, 104, 105, 110, 116, 47, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 75, 116, 10, 42, 76, 10, 50, 56, 35, 49, 58, 51, 48, 10, 50, 56, 35, 49, 58, 51, 49, 44, 51, 10, 50, 56, 35, 49, 58, 51, 52, 44, 50, 10, 42, 69, 10};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute(byteContainer, 1);
        byte[] debugExtension = sourceDebugExtensionAttribute.getDebugExtension();
        byte[] target = new byte[]{83, 77, 65, 80, 10, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 46, 107, 116, 10, 75, 111, 116, 108, 105, 110, 10, 42, 83, 32, 75, 111, 116, 108, 105, 110, 10, 42, 70, 10, 43, 32, 49, 32, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 46, 107, 116, 10, 111, 114, 103, 47, 115, 112, 114, 105, 110, 103, 102, 114, 97, 109, 101, 119, 111, 114, 107, 47, 97, 111, 116, 47, 104, 105, 110, 116, 47, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 75, 116, 10, 43, 32, 50, 32, 95, 65, 114, 114, 97, 121, 115, 46, 107, 116, 10, 107, 111, 116, 108, 105, 110, 47, 99, 111, 108, 108, 101, 99, 116, 105, 111, 110, 115, 47, 65, 114, 114, 97, 121, 115, 75, 116, 95, 95, 95, 65, 114, 114, 97, 121, 115, 75, 116, 10, 43, 32, 51, 32, 65, 114, 114, 97, 121, 115, 74, 86, 77, 46, 107, 116, 10, 107, 111, 116, 108, 105, 110, 47, 99, 111, 108, 108, 101, 99, 116, 105, 111, 110, 115, 47, 65, 114, 114, 97, 121, 115, 75, 116, 95, 95, 65, 114, 114, 97, 121, 115, 74, 86, 77, 75, 116, 10, 42, 76, 10, 49, 35, 49, 44, 50, 57, 58, 49, 10, 49, 49, 51, 51, 53, 35, 50, 58, 51, 48, 10, 49, 49, 54, 55, 48, 35, 50, 44, 51, 58, 51, 49, 10, 51, 55, 35, 51, 44, 50, 58, 51, 52, 10, 42, 83, 32, 75, 111, 116, 108, 105, 110, 68, 101, 98, 117, 103, 10, 42, 70, 10, 43, 32, 49, 32, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 46, 107, 116, 10, 111, 114, 103, 47, 115, 112, 114, 105, 110, 103, 102, 114, 97, 109, 101, 119, 111, 114, 107, 47, 97, 111, 116, 47, 104, 105, 110, 116, 47, 74, 100, 107, 80, 114, 111, 120, 121, 72, 105, 110, 116, 69, 120, 116, 101, 110, 115, 105, 111, 110, 115, 75, 116, 10, 42, 76, 10, 50, 56, 35, 49, 58, 51, 48, 10, 50, 56, 35, 49, 58, 51, 49, 44, 51, 10, 50, 56, 35, 49, 58, 51, 52, 44, 50, 10, 42, 69, 10};
        assertArrayEquals(target, debugExtension);
    }

}