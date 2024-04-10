package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class NestMemberAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(NestMemberAttribute.class);
    private int numberOfClasses;

    private int[] classes;

    private int endIndex;

    public NestMemberAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numberOfClasses = ByteUtils.bytesToInt(bc.next(2));
        classes = new int[numberOfClasses];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("NestMemberAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public int[] getClasses() {
        return classes;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numberOfClasses:{}", numberOfClasses);
        for (int i : classes) {
            Formatter formatter = new Formatter();
            formatter.format("class:|%03d|", i);
            log.info("{}", formatter);
        }
    }
}
