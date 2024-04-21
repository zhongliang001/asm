package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class PathTable {

    private final Logger logger = LoggerFactory.getLogger(PathTable.class);
    private int typePathKind;
    private int typeArgumentIndex;

    private int startIndex;

    private int endIndex;

    public PathTable(ByteContainer bc) {
        startIndex = bc.getIndex();
        typePathKind = ByteUtils.byteToUnsignedInt(bc.next());
        typeArgumentIndex = ByteUtils.byteToUnsignedInt(bc.next());
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("PathTable code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getTypePathKind() {
        return typePathKind;
    }

    public int getTypeArgumentIndex() {
        return typeArgumentIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("typePathKind: %d, typeArgumentIndex: |%03d|", typePathKind, typeArgumentIndex);
        log.info("{}", formatter);
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\t\t\t\t\ttypePathKind\t%d\ttypeArgumentIndex\t%d\n", typePathKind, typeArgumentIndex);
        stringBuilder.append(formatter);
    }
}
