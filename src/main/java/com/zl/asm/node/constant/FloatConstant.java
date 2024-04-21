package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class FloatConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(FloatConstant.class);
    private int index;
    private int tag;
    private byte[] bytes;
    private String value;

    private float fValue;

    private int startIndex;
    private int endIndex;

    public FloatConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        this.bytes = bc.next(4);
        if (logger.isDebugEnabled()) {
            log(logger, false);
        }
        fValue = ByteUtils.bytesToFloat(bytes);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("FloatConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public float getfValue() {
        return fValue;
    }

    @Override
    public String getValue() {
        return String.valueOf(fValue);
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\tvalue=%f", index, FloatConstant.class.getSimpleName(), fValue);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{},tag:{}bytes:{}", index, tag, ByteUtils.bytesToInt(bytes));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t|%03d|\t|%s|\t\tvalue=%f\n", index, FloatConstant.class.getSimpleName(), fValue);
        stringBuilder.append(formatter);
    }
}
