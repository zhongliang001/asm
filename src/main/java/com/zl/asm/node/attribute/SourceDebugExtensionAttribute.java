package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceDebugExtensionAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(SourceDebugExtensionAttribute.class);

    private byte[] debugExtension;

    private int endIndex;

    public SourceDebugExtensionAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        debugExtension = bc.next(attributeLength);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("SourceDebugExtensionAttribute code: {}", bc.copy(startIndex, endIndex));

        }
    }

    public byte[] getDebugExtension() {
        return debugExtension;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("debugExtension: {}", debugExtension);
    }
}
