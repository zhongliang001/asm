package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class UnkownAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(UnkownAttribute.class);

    private byte[] bytes;

    private int endIndex;

    private ByteContainer byteContainer;

    public UnkownAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.byteContainer = bc;
        bytes = bc.next(this.attributeLength);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("UnkownAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tUnkownAttribute\tHexcode\t%s\n",
                ByteUtils.toHexString(byteContainer.copy(startIndex, endIndex)));
        stringBuilder.append(formatter);

    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("bytes: {}", bytes);
    }
}
