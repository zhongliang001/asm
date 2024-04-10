package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InnerClassAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(InnerClassAttribute.class);
    private int numberOfClasses;

    private ClassTable[] classTables;

    private int endIndex;

    public InnerClassAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numberOfClasses = ByteUtils.bytesToInt(bc.next(2));
        classTables = new ClassTable[numberOfClasses];
        for (int i = 0; i < classTables.length; i++) {
            classTables[i] = new ClassTable(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("InnerClassAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public ClassTable[] getClassTables() {
        return classTables;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numberOfClasses:{}", numberOfClasses);
        for (ClassTable table : classTables) {
            table.log(log, isParent);
        }
    }
}
