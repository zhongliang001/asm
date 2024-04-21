package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class SourceDebugExtensionAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(SourceDebugExtensionAttribute.class);

    private byte[] debugExtension;

    private int endIndex;

    private ByteContainer bc;

    public SourceDebugExtensionAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.bc = bc;
        debugExtension = bc.next(attributeLength);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("SourceDebugExtensionAttribute code: {}", bc.copy(startIndex, endIndex));

        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tSourceDebugExtensionAttribute\tHexcode\t%s\n",
                ByteUtils.toHexString(bc.copy(startIndex, endIndex)));
        stringBuilder.append(formatter);

    }


    public byte[] getDebugExtension() {
        return debugExtension;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("debugExtension: {}", debugExtension);
    }
}
