package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class DoubleConstant extends ConstantNode {

    private Logger logger = LoggerFactory.getLogger(DoubleConstant.class);
    private int index;
    private int tag;

    private double dValue;
    private int startIndex;

    private int endIndex;

    public DoubleConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        dValue = ByteUtils.bytesToDouble(bc.next(8));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("DoubleConstant code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public double getdValue() {
        return dValue;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("index: |%03d|, %s, value: %f", index, DoubleConstant.class.getSimpleName(), dValue);
        log.info("{}", formatter);
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t|%03d|\t|%s|\tvalue=%f\n", index, DoubleConstant.class.getSimpleName(), dValue);
        stringBuilder.append(formatter);
    }

    @Override
    public String getValue() {
        return String.valueOf(dValue);
    }

}
