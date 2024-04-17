package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class TypeParameterBoundTarget {

    private final Logger logger = LoggerFactory.getLogger(TypeParameterBoundTarget.class);
    private int typeParameterIndex;

    private int boundIndex;

    private int startIndex;

    private int endIndex;

    public TypeParameterBoundTarget(ByteContainer bc) {
        startIndex = bc.getIndex();
        typeParameterIndex = ByteUtils.byteToUnsignedInt(bc.next());
        boundIndex = ByteUtils.byteToUnsignedInt(bc.next());
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("TypeParameterBoundTarget code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getTypeParameterIndex() {
        return typeParameterIndex;
    }

    public int getBoundIndex() {
        return boundIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("typeParameterIndex: |%03d|,boundIndex: |%03d| ", typeParameterIndex, boundIndex);
        log.info("{}", formatter);
    }
}
