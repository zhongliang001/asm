package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Utf8Constant extends ConstantNode {

    private Logger logger = LoggerFactory.getLogger(Utf8Constant.class);
    private int tag;
    private int index;
    private int length;
    private byte[] bytes;
    private String value;

    private int startIndex;

    private int endIndex;

    public Utf8Constant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.tag = tag;
        this.index = index;
        this.length = ByteUtils.bytesToInt(bc.next(2));
        this.bytes = bc.next(this.length);
        if (logger.isDebugEnabled()) {
            log(logger);
        }
        if (this.length != 0) {
            this.value = new String(this.bytes);
        }
        endIndex = bc.getIndex() - 1;

        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Utf8Constant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t\tvalue=%s", index, Utf8Constant.class.getSimpleName(), this.value);
            log.info("{}", formatter);
        } else {
            log.info("index:{},tag:{},length:{},bytes:{}", index, tag, length, this.value);

        }

    }

    @Override
    public String getValue() {
        return this.value;
    }
}
