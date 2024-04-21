package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class NestMemberAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(NestMemberAttribute.class);
    private int numberOfClasses;

    private int[] classes;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public NestMemberAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        numberOfClasses = ByteUtils.bytesToInt(bc.next(2));
        classes = new int[numberOfClasses];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("NestMemberAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public int[] getClasses() {
        return classes;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\tNestMemberAttribute\tnumberOfClasses\t%s\n", numberOfClasses);
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        formatter.format("\t\t\t");
        for (int aClass : classes) {
            ConstantNode constantNode = constantNodes[aClass - 1];
            formatter.format("%s\t", constantNode.getValue());
        }
        formatter.format("\n");
        stringBuilder.append(formatter);

    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numberOfClasses:{}", numberOfClasses);
        for (int i : classes) {
            Formatter formatter = new Formatter();
            formatter.format("class:|%03d|", i);
            log.info("{}", formatter);
        }
    }
}
