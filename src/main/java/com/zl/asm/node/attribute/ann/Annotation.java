package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Annotation implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Annotation.class);
    private int typeIndex;

    private int numElementValuePairs;

    private ElementValuePair[] elementValuePairs;

    private int startIndex;

    private int endIndex;

    public Annotation(ByteContainer bc) {
        startIndex = bc.getIndex();
        typeIndex = ByteUtils.bytesToInt(bc.next(2));
        numElementValuePairs = ByteUtils.bytesToInt(bc.next(2));
        elementValuePairs = new ElementValuePair[numElementValuePairs];
        for (int i = 0; i < elementValuePairs.length; i++) {
            elementValuePairs[i] = new ElementValuePair(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Annotation code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public int getNumElementValuePairs() {
        return numElementValuePairs;
    }

    public ElementValuePair[] getElementValuePairs() {
        return elementValuePairs;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("typeIndex:|%03d|,numElementValuePairs: %d", typeIndex, numElementValuePairs);
        log.info("{}", log);
        for (ElementValuePair elementValuePair : elementValuePairs) {
            elementValuePair.log(log, true);
        }
    }

    @Override
    public void accept(Reader reader) {

    }
}
