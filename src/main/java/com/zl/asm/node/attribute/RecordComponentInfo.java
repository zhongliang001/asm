package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class RecordComponentInfo {

    private final Logger logger = LoggerFactory.getLogger(RecordComponentInfo.class);
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private Attribute[] attributes;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public RecordComponentInfo(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
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
            logger.info("RecordComponentInfo code: {}", bc.copy(startIndex, endIndex));
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
        formatter.format("nameIndex: |%03d|, descriptorIndex: |%03d|, attributesCount: %d", nameIndex, descriptorIndex, attributesCount);
        log.info("{}", formatter);
        for (Attribute attribute : attributes) {
            attribute.log(log, isParent);
        }

    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode nameConstantNode = constantNodes[nameIndex - 1];
        ConstantNode descriConstantNode = constantNodes[descriptorIndex - 1];
        formatter.format("\t\tname\t%s\tdescriptorIndex\t%s\tattributesCount:\t%d\n", nameConstantNode.getValue(), descriConstantNode.getValue(), attributesCount);
        stringBuilder.append(formatter);
        for (Attribute attribute : attributes) {
            attribute.getLog(stringBuilder);
        }
    }
}
