package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ExceptionNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeAttributeTest {

    byte[] codeByte = new byte[]{0, 0, 0, 57, 0, 2, 0, 1, 0, 0, 0, 11, 42, -73, 0, 1, 42, 16, 10, -75, 0, 2, -79, 0, 0, 0, 2, 0, 15, 0, 0, 0, 10, 0, 2, 0, 0, 0, 3, 0, 4, 0, 6, 0, 16, 0, 0, 0, 12, 0, 1, 0, 0, 0, 11, 0, 17, 0, 18, 0, 0};
    byte[] bytes = new byte[]{0, 31, 10, 0, 4, 0, 25, 9, 0, 3, 0, 26, 7, 0, 27, 7, 0, 28, 7, 0, 29, 7, 0, 30, 1, 0, 6,
            105, 110, 116, 118, 97, 108, 1, 0, 1, 73, 1, 0, 13, 67, 111, 110, 115, 116, 97, 110, 116, 86, 97, 108, 117,
            101, 3, 0, 0, 0, 10, 1, 0, 7, 105, 110, 116, 118, 97, 108, 50, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3,
            40, 41, 86, 1, 0, 4, 67, 111, 100, 101, 1, 0, 15, 76, 105, 110, 101, 78, 117, 109, 98, 101, 114, 84, 97, 98,
            108, 101, 1, 0, 18, 76, 111, 99, 97, 108, 86, 97, 114, 105, 97, 98, 108, 101, 84, 97, 98, 108, 101, 1, 0, 4,
            116, 104, 105, 115, 1, 0, 19, 76, 99, 111, 109, 47, 122, 108, 47, 72, 101, 108, 108, 111, 87, 111, 114, 108,
            100, 59, 1, 0, 4, 116, 101, 115, 116, 1, 0, 1, 97, 1, 0, 1, 98, 1, 0, 1, 99, 1, 0, 10, 83, 111, 117, 114, 99,
            101, 70, 105, 108, 101, 1, 0, 15, 72, 101, 108, 108, 111, 87, 111, 114, 108, 100, 46, 106, 97, 118, 97, 12,
            0, 12, 0, 13, 12, 0, 11, 0, 8, 1, 0, 17, 99, 111, 109, 47, 122, 108, 47, 72, 101, 108, 108, 111, 87, 111,
            114, 108, 100, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 1, 0, 19,
            106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 67, 108, 111, 110, 101, 97, 98, 108, 101, 1, 0, 13, 99, 111,
            109, 47, 122, 108, 47, 84, 101, 115, 116, 73, 110};
    byte[] codes = new byte[]{42, -73, 0, 1, 42, 16, 10, -75, 0, 2, -79};

    @Test
    public void test() {
        ByteContainer nodeByteContainer = new ByteContainer(bytes);
        ConstantPoolNode node = new ConstantPoolNode(nodeByteContainer);
        ByteContainer codeByteContainer = new ByteContainer(codeByte);
        CodeAttribute codeAttribute = new CodeAttribute(codeByteContainer, node, 1);
        int maxStack = codeAttribute.getMaxStack();
        assertEquals(2, maxStack);
        int maxLocals = codeAttribute.getMaxLocals();
        assertEquals(1, maxLocals);
        int codeLength = codeAttribute.getCodeLength();
        assertEquals(11, codeLength);
        byte[] code = codeAttribute.getCode();
        assertArrayEquals(codes, code);
        int attributesCount = codeAttribute.getAttributesCount();
        assertEquals(2, attributesCount);
        Attribute[] attributes = codeAttribute.getAttributes();
        assertEquals(2, attributes.length);
        int exceptionTableLength = codeAttribute.getExceptionTableLength();
        assertEquals(0, exceptionTableLength);
        ExceptionNode[] exceptionNodes = codeAttribute.getExceptionNodes();
        assertEquals(0, exceptionNodes.length);
    }
}