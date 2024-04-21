package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.constant.ConstantPoolNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationDefaultAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(AnnotationDefaultAttribute.class);
    private ElementValue elementValue;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public AnnotationDefaultAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        elementValue = new ElementValue(bc, constantPoolNode);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("AnnotationDefaultAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public ElementValue getElementValue() {
        return elementValue;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        elementValue.getLog(stringBuilder);
    }


    @Override
    public void log(Logger log, boolean isParent) {
        elementValue.log(log, true);
    }
}
