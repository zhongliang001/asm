package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.attribute.AttributeFactory;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class FieldItem {
    private final Logger logger = LoggerFactory.getLogger(FieldItem.class);
    private AccessFlag accessFlags;

    private int nameIndex;

    private int descriptorIndex;

    private int attributesCount;

    private Attribute[] attributes;

    private int startIndex;
    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public FieldItem(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        this.startIndex = bc.getIndex();
        accessFlags = new AccessFlag(bc, AccessFlagType.FIELD_ACCESS_FLAG);
        nameIndex = ByteUtils.bytesToInt(bc.next(2));
        descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        attributesCount = ByteUtils.bytesToInt(bc.next(2));
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
            int i1 = ByteUtils.bytesToInt(bc.next(2));
            ConstantNode constantNode = constantNodes[i1 - 1];
            String value = constantNode.getValue();
            attributes[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
        }
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("FieldItem code:{}", bc.copy(startIndex, endIndex));
        }
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
        formatter.format("nameIndex:%03d|descriptorIndex:%03d|，attributesCount:%03d|", nameIndex, descriptorIndex, attributesCount);
        log.info("{}", formatter);
        for (Attribute attributeVisitor : attributes) {
            attributeVisitor.log(log, true);
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tAccessFlag: %s\n", accessFlags.getAccessFlags().toArray().toString());
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode nameConstantNode = constantNodes[nameIndex - 1];
        String name = nameConstantNode.getValue();
        ConstantNode descriptConstantNode = constantNodes[descriptorIndex - 1];
        String descript = descriptConstantNode.getValue();

        formatter.format("\t%s\t%s\t\n\tattributesCount:%d\n", name, descript, attributesCount);
        stringBuilder.append(formatter);
        for (Attribute attributeVisitor : attributes) {
            attributeVisitor.getLog(stringBuilder);
        }
    }
}
