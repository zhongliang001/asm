package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class MethodConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(MethodConstant.class);

    private int tag;

    private int index;
    // 指向类名
    private int classIndex;

    // 指向方法
    private int name_and_type_index;

    public MethodConstant(ByteContainer bc, int tag, int index) {
        super(bc);
        this.index = index;
        this.tag = tag;
        classIndex = ByteUtils.bytesToInt(bc.next(2));
        name_and_type_index = ByteUtils.bytesToInt(bc.next(2));
        if (logger.isDebugEnabled()) {
            log(logger, false);
        }
    }

    public void accept() {
        logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, classIndex, name_and_type_index);
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, MethodConstant.class.getSimpleName(), classIndex, name_and_type_index);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, classIndex, name_and_type_index);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
