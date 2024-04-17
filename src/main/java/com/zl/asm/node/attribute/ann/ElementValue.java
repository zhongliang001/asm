package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ElementValue {

    private final Logger logger = LoggerFactory.getLogger(ElementValue.class);
    private char tag;

    private int constValueIndex;

    private EnumConstValue enumConstValue;

    private int classInfoIndex;

    private Annotation annotation;

    private ArrayValue arrayValue;

    private int startIndex;

    private int endIndex;

    public ElementValue(ByteContainer bc) {
        startIndex = bc.getIndex();
        tag = (char) bc.next();
        if (tag == 'e') {
            enumConstValue = new EnumConstValue(bc);
        } else if (tag == '@') {
            annotation = new Annotation(bc);
        } else if (tag == '[') {
            arrayValue = new ArrayValue(bc);
        } else if (tag == 'c') {
            classInfoIndex = ByteUtils.bytesToInt(bc.next(2));
        } else {
            constValueIndex = ByteUtils.bytesToInt(bc.next(2));
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ElementValue code: {}", bc.copy(startIndex, endIndex));
        }

    }

    public char getTag() {
        return tag;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public EnumConstValue getEnumConstValue() {
        return enumConstValue;
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public ArrayValue getArrayValue() {
        return arrayValue;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        if (tag == 'e') {
            enumConstValue.log(log, true);
        } else if (tag == '@') {
            annotation.log(log, true);
        } else if (tag == '[') {
            arrayValue.log(log, true);
        } else if (tag == 'c') {
            formatter.format("classInfoIndex: |%03d|", classInfoIndex);
        } else {
            formatter.format("constValueIndex: |%03d|", constValueIndex);
        }
    }
}
