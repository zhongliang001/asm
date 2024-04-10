package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class MethodHandleConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(MethodHandleConstant.class);
    private int tag;
    private int index;

    private int referenceKind;
    private int referenceIndex;

    private int startIndex;

    private int endIndex;


    public MethodHandleConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.tag = tag;
        this.index = index;
        referenceKind = ByteUtils.byteToUnsignedInt(bc.next());
        referenceIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("MethodHandleConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%d|", index, MethodHandleConstant.class.getSimpleName(), referenceKind, referenceIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, referenceKind:{},referenceIndex:{}", index, tag, referenceKind, referenceIndex);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
