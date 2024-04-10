package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.stack.StackMapFrame;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackMapTableAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(StackMapTableAttribute.class);

    private int numberOfEntries;

    private StackMapFrame[] stackMapFrames;

    private int endIndex;

    public StackMapTableAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numberOfEntries = ByteUtils.bytesToInt(bc.next(2));
        stackMapFrames = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            stackMapFrames[i] = new StackMapFrame(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.info("StackMapTableAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public StackMapFrame[] getStackMapFrames() {
        return stackMapFrames;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numberOfEntries:{}", numberOfEntries);
        for (StackMapFrame stackMapFrame : stackMapFrames) {
            stackMapFrame.log(log, true);
        }
    }
}
