package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class NestHostAttribute extends Attribute {
    private final Logger logger = LoggerFactory.getLogger(NestHostAttribute.class);
    private int hostClassIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public NestHostAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        hostClassIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("hostClassIndex code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[hostClassIndex - 1];
        formatter.format("\t\tNestHostAttribute\thostClass\t%s\n", constantNode.getValue());
        stringBuilder.append(formatter);
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("NestHostAttribute:{}", hostClassIndex);
    }
}
