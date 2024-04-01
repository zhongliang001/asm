package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class FieldConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(FieldConstant.class);

    private int tag;

    private int index;
    // 指向类名
    private int classIndex;
    private int nameAndTypeIndex;

    private int startIndex;

    private int endIndex;

    public FieldConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        this.classIndex = ByteUtils.bytesToInt(bc.next(2));
        nameAndTypeIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("FieldConstant code:{}", bc.copy(startIndex, endIndex));
        }

    }


    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, FieldConstant.class.getSimpleName(), classIndex, nameAndTypeIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, classIndex, nameAndTypeIndex);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
