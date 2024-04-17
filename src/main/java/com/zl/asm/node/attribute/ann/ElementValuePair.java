package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ElementValuePair {

    private final Logger logger = LoggerFactory.getLogger(ElementValue.class);

    private int elementNameIndex;

    private ElementValue value;

    private int startIndex;

    private int endIndex;

    public ElementValuePair(ByteContainer bc) {
        startIndex = bc.getIndex();
        elementNameIndex = ByteUtils.bytesToInt(bc.next(2));
        value = new ElementValue(bc);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ElementValuePair code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getElementNameIndex() {
        return elementNameIndex;
    }

    public ElementValue getValue() {
        return value;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("elementNameIndex:|%03d|", elementNameIndex);
        log.info("{}", formatter);
        value.log(log, true);
    }
}
