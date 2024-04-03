package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ConstantAttribute extends Attribute {
    private final Logger logger = LoggerFactory.getLogger(ConstantAttribute.class);
    private int constantvalueIndex;

    private int endIndex;


    public ConstantAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
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
