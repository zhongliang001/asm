package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class MethodTypeConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(MethodTypeConstant.class);
    private int tag;

    private int descriptorIndex;

    private int index;

    private int startIndex;

    private int endIndex;

    public MethodTypeConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("MethodTypeConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("|%03d|\t|%s|\t|%03d|", index, MethodTypeConstant.class.getSimpleName(), descriptorIndex);
        logger.info("{}", formatter);
    }

    @Override
    public String getValue() {
        return null;
    }
}
