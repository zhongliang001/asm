package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class InterfaceMethodrefConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(InterfaceMethodrefConstant.class);

    private int tag;

    private int index;

    private int classIndex;
    // 指向方法
    private int nameAndTypeIndex;

    private int startIndex;

    private int endIndex;

    public InterfaceMethodrefConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        classIndex = ByteUtils.bytesToInt(bc.next(2));
        nameAndTypeIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("MethodConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, MethodConstant.class.getSimpleName(), classIndex, nameAndTypeIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, classIndex, nameAndTypeIndex);
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {

    }

    @Override
    public String getValue() {
        return null;
    }
}
