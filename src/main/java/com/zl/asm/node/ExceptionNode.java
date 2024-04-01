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

    public ExceptionNode(ByteContainer bc) {
        //        super(bc);
        startPc = ByteUtils.bytesToInt(bc.next(2));
        endPc = ByteUtils.bytesToInt(bc.next(2));
        handlerPc = ByteUtils.bytesToInt(bc.next(2));
        catchType = ByteUtils.bytesToInt(bc.next(2));
    }

    // @Override
    public void accept() {
        logger.info("startPc:{}, endPc:{}，handlerPc：{}，catchType：{}", startPc, endPc, handlerPc, catchType);
    }

    @Override
    public void accept(Reader reader) {

    }
}