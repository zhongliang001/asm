package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ConstantAttribute extends Attribute {
    private final Logger logger = LoggerFactory.getLogger(ConstantAttribute.class);
    private int constantvalueIndex;

    public ConstantAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        constantvalueIndex = ByteUtils.bytesToInt(bc.next(2));
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("attributeNameIndex:|%03d|,attributeLength:%d,constantvalueIndex:|%03d|", attributeNameIndex, attributeLength, constantvalueIndex);
        log.info("{}", formatter);
    }


}
