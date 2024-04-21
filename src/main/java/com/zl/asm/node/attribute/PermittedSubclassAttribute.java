package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class PermittedSubclassAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(PermittedSubclassAttribute.class);

    private int numberOfClasses;

    private int[] classes;

    private int endIndex;

    private ByteContainer bc;

    public PermittedSubclassAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.bc = bc;
        numberOfClasses = ByteUtils.bytesToInt(bc.next(2));
        classes = new int[numberOfClasses];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        endIndex = bc.getIndex();
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("PermittedSubclassAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public int[] getClasses() {
        return classes;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tPermittedSubclassAttribute\tHexcode\t%s\n",
                ByteUtils.toHexString(bc.copy(startIndex, endIndex)));
        stringBuilder.append(formatter);
    }

    @Override
    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("numberOfClasses: %d, classes:", numberOfClasses);
        for (int aClass : classes) {
            formatter.format("|%03d|", aClass);
        }
        log.info("{}", formatter);
    }
}
