package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class EnclosingMethodAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(EnclosingMethodAttribute.class);

    private int classIndex;
    private int methodIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public EnclosingMethodAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        classIndex = ByteUtils.bytesToInt(bc.next(2));
        methodIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("EnclosingMethodAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[classIndex - 1];
        formatter.format("\t\tEnclosingMethodAttribute\tclass\t%s", constantNode.getValue());
        if (methodIndex > 1) {
            ConstantNode methodConstantNode = constantNodes[methodIndex - 1];
            formatter.format("\tmethod\t%s", methodConstantNode.getValue());
        }
        stringBuilder.append(formatter).append("\n");
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("classIndex:{},methodIndex:{}", classIndex, methodIndex);
    }
}
