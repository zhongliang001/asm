package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.attribute.AttributeFactory;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class FieldItem implements ClassNode {
    private final Logger logger = LoggerFactory.getLogger(FieldItem.class);
    private AccessFlagsVisitor accessFlagsVisitor;

    private int nameIndex;

    private int descriptorIndex;

    private int attributesCount;

    private Attribute[] attributeVisitors;

    public FieldItem(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        accessFlagsVisitor = new AccessFlagsVisitor(bc, AccessFlagType.FIELD_ACCESS_FLAG);
        nameIndex = ByteUtils.bytesToInt(bc.next(2));
        descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        attributesCount = ByteUtils.bytesToInt(bc.next(2));
        attributeVisitors = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
            int i1 = ByteUtils.bytesToInt(bc.next(2));
            ConstantNode constantVisitor = constantVisitors[i1 - 1];
            String value = constantVisitor.getValue();
            attributeVisitors[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
        }
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("nameIndex:%03d|descriptorIndex:%03d|，attributesCount:%03d|", nameIndex, descriptorIndex, attributesCount);
        log.info("{}", formatter);
        for (Attribute attributeVisitor : attributeVisitors) {
            attributeVisitor.log(log, true);
        }
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
        for (Attribute attributeVisitor : attributeVisitors) {
            attributeVisitor.accept(reader);
        }
    }
}
