package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

import java.util.Formatter;

public class FieldConstant extends ConstantNode {

    private int tag;

    private int index;
    // 指向类名
    private int classIndex;

    private int nameAndTypeIndex;

    public FieldConstant(ByteContainer bc, int tag, int index) {
        super(bc);
        this.index = index;
        this.tag = tag;
        this.classIndex = ByteUtils.bytesToInt(bc.next(2));
        nameAndTypeIndex = ByteUtils.bytesToInt(bc.next(2));
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, FieldConstant.class.getSimpleName(), classIndex, nameAndTypeIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, classIndex);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
