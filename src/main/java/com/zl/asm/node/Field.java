package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Field implements ClassNode {
    private final Logger logger = LoggerFactory.getLogger(Field.class);
    private int fieldsCount;

    private FieldItem[] fieldItemVisitors;

    public Field(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.fieldsCount = ByteUtils.bytesToInt(bc.next(2));
        fieldItemVisitors = new FieldItem[this.fieldsCount];
        for (int i = 0; i < fieldItemVisitors.length; i++) {
            fieldItemVisitors[i] = new FieldItem(bc, constantPoolNode);
        }
        if (logger.isDebugEnabled()) {
            log(logger);
        }
    }

    @Override
    public void log(Logger log) {
        log.info("fieldsCount:{}", fieldsCount);
        for (int i = 0; i < fieldItemVisitors.length; i++) {
            fieldItemVisitors[i].log(log, true);
        }
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}