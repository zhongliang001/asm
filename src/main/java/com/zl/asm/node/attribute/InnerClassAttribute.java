package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class InnerClassAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(InnerClassAttribute.class);
    private int numberOfClasses;

    private ClassTable[] classTables;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public InnerClassAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numberOfClasses = ByteUtils.bytesToInt(bc.next(2));
        classTables = new ClassTable[numberOfClasses];
        for (int i = 0; i < classTables.length; i++) {
            classTables[i] = new ClassTable(bc, constantPoolNode);
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
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tnumberOfInnerClass\t%d\n", numberOfClasses);
        stringBuilder.append(formatter);
        for (ClassTable classTable : classTables) {
            classTable.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numberOfClasses:{}", numberOfClasses);
        for (ClassTable table : classTables) {
            table.log(log, isParent);
        }
    }

    @Override
    public void log(Logger logger) {

    }
}
