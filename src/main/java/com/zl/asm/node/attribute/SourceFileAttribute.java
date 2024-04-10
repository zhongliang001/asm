package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class SourceFileAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(SourceFileAttribute.class);
    private int sourcefileIndex;

    private int endIndex;

    public SourceFileAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.sourcefileIndex = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("SourceFileAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getSourcefileIndex() {
        return sourcefileIndex;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("sourcefileIndex:|%03d|", sourcefileIndex);
        log.info("{}", formatter);
    }
}
