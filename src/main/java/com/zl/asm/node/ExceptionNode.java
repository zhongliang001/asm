package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionNode implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(ExceptionNode.class);

    private int startPc;

    private int endPc;

    private int handlerPc;

    private int catchType;

    private int startIndex;

    private int endIndex;

    public ExceptionNode(ByteContainer bc) {
        startIndex = bc.getIndex();
        startPc = ByteUtils.bytesToInt(bc.next(2));
        endPc = ByteUtils.bytesToInt(bc.next(2));
        handlerPc = ByteUtils.bytesToInt(bc.next(2));
        catchType = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.info("ExceptionNode code:{}", bc.copy(startIndex, endIndex));
        }
    }

    // @Override
    public void accept() {
        logger.info("startPc:{}, endPc:{}，handlerPc：{}，catchType：{}", startPc, endPc, handlerPc, catchType);
    }

    public void log(Logger log, boolean isParent) {
        logger.info("startPc:{}, endPc:{}，handlerPc：{}，catchType：{}", startPc, endPc, handlerPc, catchType);
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}