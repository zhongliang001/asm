package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ClassConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(ClassConstant.class);

    private int index;
    private int tag;
    // 指向类名
    private int classIndex;

    public ClassConstant(ByteContainer bc, int tag, int index) {
        super(bc);
        this.index = index;
        this.tag = tag;
        classIndex = ByteUtils.bytesToInt(bc.next(2));
        if (logger.isDebugEnabled()) {
            log(logger, false);
        }
    }

    public void accept() {
        logger.info("index:{},tag:{}classIndex:{}", index, tag, classIndex);
    }

    public void log(Logger logger, boolean isParent) {
        if(isParent){
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|", index, ClassConstant.class.getSimpleName(), classIndex);
            logger.info("{}", formatter);
        }else{
            logger.info("index:{}, tag:{}, classIndex:{}", index, tag, classIndex);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
