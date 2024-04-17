package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayValue {

    private final Logger logger = LoggerFactory.getLogger(ArrayValue.class);
    private int numValues;

    private ElementValue[] elementValues;

    private int startIndex;

    private int endIndex;

    public ArrayValue(ByteContainer bc) {
        startIndex = bc.getIndex();
        numValues = ByteUtils.bytesToInt(bc.next(2));
        elementValues = new ElementValue[numValues];
        for (int i = 0; i < elementValues.length; i++) {
            elementValues[i] = new ElementValue(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ArrayValue code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumValues() {
        return numValues;
    }

    public ElementValue[] getElementValues() {
        return elementValues;
    }

    public void log(Logger log, boolean isParent) {
        for (ElementValue elementValue : elementValues) {
            elementValue.log(log, true);
        }
    }
}
