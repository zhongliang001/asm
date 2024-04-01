package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;

public class AttributeFactory {

    public static Attribute getAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, String type, int attributeNameIndex) {
        switch (type) {
            case AttributeType.CONSTANT_VALUE:
                return new ConstantAttribute(bc, attributeNameIndex);
            case AttributeType.CODE:
                return new CodeAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.LINENUMBERTABLE:
                return new LineNumberTableAttribute(bc, attributeNameIndex);
            case AttributeType.LOCALVARIABLETABLE:
                return new LocalVariableTableAttribute(bc, attributeNameIndex);
            case AttributeType.SOURCEFILE:
                return new SourceFileAttribute(bc, attributeNameIndex);
            default:
                throw new RuntimeException();
        }
    }
}
