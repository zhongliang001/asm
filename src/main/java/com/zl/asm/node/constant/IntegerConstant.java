package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class IntegerConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(IntegerConstant.class);
    private int index;
    private int tag;
    private byte[] bytes;
    private String value;

    private int startIndex;
    private int endIndex;

    public IntegerConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        this.bytes = bc.next(Integer.BYTES);
        if (logger.isDebugEnabled()) {
            log(logger, false);
        }
        value = String.valueOf(ByteUtils.bytesToInt(bytes));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("IntegerConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\tvalue=%d", index, IntegerConstant.class.getSimpleName(), ByteUtils.bytesToInt(bytes));
            logger.info("{}", formatter);
        } else {
            logger.info("index:{},tag:{}bytes:{}", index, tag, ByteUtils.bytesToInt(bytes));
        }


    }
}
