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

    private FieldItem[] fieldItems;

    private int startIndex;
    private int endIndex;


    public Field(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.startIndex = bc.getIndex();
        this.fieldsCount = ByteUtils.bytesToInt(bc.next(2));
        fieldItems = new FieldItem[this.fieldsCount];
        for (int i = 0; i < fieldItems.length; i++) {
            fieldItems[i] = new FieldItem(bc, constantPoolNode);
        }
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.info("Field code:{}", bc.copy(startIndex, endIndex));
        }

    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public FieldItem[] getFieldItems() {
        return fieldItems;
    }

    @Override
    public void log(Logger log) {
        log.info("fieldsCount:{}", fieldsCount);
        for (FieldItem fieldItem : fieldItems) {
            fieldItem.log(log, true);
        }
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}