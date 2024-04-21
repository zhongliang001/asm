package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class TypeArgumentTarget {
    private final Logger logger = LoggerFactory.getLogger(TypeArgumentTarget.class);
    private int offset;
    private int typeArgumentIndex;

    private int startIndex;

    private int endIndex;

    public TypeArgumentTarget(ByteContainer bc) {
        startIndex = bc.getIndex();
        offset = ByteUtils.bytesToInt(bc.next(2));
        typeArgumentIndex = ByteUtils.byteToUnsignedInt(bc.next());
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("TypeArgumentTarget code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getOffset() {
        return offset;
    }

    public int getTypeArgumentIndex() {
        return typeArgumentIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("offset: %d, typeArgumentIndex: |%03d|", offset, typeArgumentIndex);

        log.info("{}", formatter);

    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\t\t\t\toffset\t%d\ttypeArgumentIndex\t%d\n", offset, typeArgumentIndex);
        stringBuilder.append(formatter);
    }
}
