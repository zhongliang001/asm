package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LongConstant extends ConstantNode {
    private Logger logger = LoggerFactory.getLogger(LongConstant.class);
    private int index;
    private int tag;

    private long highBytes;

    private long lowBytes;
    private int startIndex;

    private int endIndex;

    public LongConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        highBytes = ByteUtils.bytesToLong(bc.next(4)) << 32;
        lowBytes = ByteUtils.bytesToLong(bc.next(4));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LongConstant code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public long getHighBytes() {
        return highBytes;
    }

    public long getLowBytes() {
        return lowBytes;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("index: |%03d|, %s, highBytes: %d, lowBytes: %d", index, LongConstant.class.getSimpleName(), highBytes, lowBytes);
        log.info("{}", formatter);
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t|%03d|\t|%s|\tvalue=%d\n", index, LongConstant.class.getSimpleName(), highBytes + lowBytes);
        stringBuilder.append(formatter);
    }

    @Override
    public String getValue() {
        return null;
    }
}
