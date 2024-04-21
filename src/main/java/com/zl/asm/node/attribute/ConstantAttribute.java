package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ConstantAttribute extends Attribute {
    private final Logger logger = LoggerFactory.getLogger(ConstantAttribute.class);
    private int constantvalueIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;


    public ConstantAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        constantvalueIndex = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ConstantAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }


    public int getConstantvalueIndex() {
        return constantvalueIndex;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode nameConstantNode = constantNodes[attributeNameIndex - 1];
        String name = nameConstantNode.getValue();
        ConstantNode descriptConstantNode = constantNodes[constantvalueIndex - 1];
        String descript = descriptConstantNode.getValue();
        formatter.format("\t%s\t%s\n", name, descript);
        stringBuilder.append(formatter);
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("attributeNameIndex:|%03d|,attributeLength:%d,constantvalueIndex:|%03d|", attributeNameIndex, attributeLength, constantvalueIndex);
        log.info("{}", formatter);
    }

    @Override
    public void log(Logger log) {
        log(logger, false);
    }
}
