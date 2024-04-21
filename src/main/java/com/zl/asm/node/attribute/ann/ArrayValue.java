package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ArrayValue {

    private final Logger logger = LoggerFactory.getLogger(ArrayValue.class);
    private int numValues;

    private ElementValue[] elementValues;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public ArrayValue(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        numValues = ByteUtils.bytesToInt(bc.next(2));
        elementValues = new ElementValue[numValues];
        for (int i = 0; i < elementValues.length; i++) {
            elementValues[i] = new ElementValue(bc, constantPoolNode);
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

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\t\tnumValues\t%s\n", numValues);
        stringBuilder.append(formatter);
        for (ElementValue elementValue : elementValues) {
            elementValue.getLog(stringBuilder);
        }

    }
}
