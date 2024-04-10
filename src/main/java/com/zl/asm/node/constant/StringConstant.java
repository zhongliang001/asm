package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class StringConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(StringConstant.class);
    private int tag;
    private int index;
    private int stringIndex;

    private int startIndex;

    private int endIndex;

    public StringConstant(ByteContainer bc, int tag, int index) {
        this.startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        this.stringIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("StringConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%03d|", index, stringIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, stringIndex:{}", index, tag, stringIndex);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
