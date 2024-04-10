package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class DynamicConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(DynamicConstant.class);
    private int tag;
    private int index;
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    private int startIndex;

    private int endIndex;


    public DynamicConstant(ByteContainer bc, int tag, int index) {
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        bootstrapMethodAttrIndex = ByteUtils.bytesToInt(bc.next(2));
        nameAndTypeIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("DynamicConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, DynamicConstant.class.getSimpleName(), bootstrapMethodAttrIndex, nameAndTypeIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, bootstrapMethodAttrIndex:{},name_and_type_index:{}", index, tag, bootstrapMethodAttrIndex, nameAndTypeIndex);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
