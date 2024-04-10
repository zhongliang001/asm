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

public class MethodItem implements ClassNode {
    private final Logger logger = LoggerFactory.getLogger(MethodItem.class);
    private AccessFlag accessFlags;
    private int nameIndex;

    private int descriptorIndex;

    private int attributesCount;

    private Attribute[] attributes;

    private int startIndex;

    private int endIndex;

    public MethodItem(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        startIndex = bc.getIndex();
        accessFlags = new AccessFlag(bc, AccessFlagType.METHOD_ACCESS_FLAG);
        nameIndex = ByteUtils.bytesToInt(bc.next(2));
        descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        attributesCount = ByteUtils.bytesToInt(bc.next(2));
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributes.length; i++) {
            ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
            int i1 = ByteUtils.bytesToInt(bc.next(2));
            ConstantNode constantVisitor = constantVisitors[i1 - 1];
            String value = constantVisitor.getValue();
            attributes[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("MethodItem:{}", bc.copy(startIndex, endIndex));
        }
    }

    public AccessFlag getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("nameIndex:|%03d|,descriptorIndex:|%s|attributesCount:%d", nameIndex, descriptorIndex, attributesCount);
        log.info("{}", formatter);
        for (Attribute attributeVisitor : attributes) {
            attributeVisitor.log(log, true);
        }
    }

    @Override
    public void accept(Reader reader) {

    }
}