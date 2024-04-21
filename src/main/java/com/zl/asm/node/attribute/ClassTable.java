package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ClassTable {

    private final Logger logger = LoggerFactory.getLogger(ClassTable.class);

    private int innerClassInfoIndex;

    private int outerClassInfoIndex;
    private int innerNameIndex;
    private AccessFlag innerClassAccessFlags;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public ClassTable(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        this.innerClassInfoIndex = ByteUtils.bytesToInt(bc.next(2));
        this.outerClassInfoIndex = ByteUtils.bytesToInt(bc.next(2));
        this.innerNameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.innerClassAccessFlags = new AccessFlag(bc, AccessFlagType.CLASS_ACCESS_FLAG);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ClassTable code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getInnerClassInfoIndex() {
        return innerClassInfoIndex;
    }

    public int getOuterClassInfoIndex() {
        return outerClassInfoIndex;
    }

    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    public AccessFlag getInnerClassAccessFlags() {
        return innerClassAccessFlags;
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("innerClassInfoIndex:|%03d|，outerClassInfoIndex:|%03d|, innerNameIndex:|%03d|, innerClassAccessFlags:%s", innerClassInfoIndex, outerClassInfoIndex, innerNameIndex, innerClassAccessFlags.getAccessFlags());
        logger.info("{}", formatter);
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\tinnerClassAccessFlags\t%s\t", innerClassAccessFlags.getAccessFlags().toArray().toString());
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[innerClassInfoIndex - 1];
        formatter.format("\t\t\tinnerClass\t%s\t", constantNode.getValue());
        if (innerNameIndex > 0) {
            ConstantNode innerConstantNode = constantNodes[innerNameIndex - 1];
            formatter.format("innerClassName\t%s\t", innerConstantNode.getValue());
        }
        if (outerClassInfoIndex > 1) {
            ConstantNode outerConstantNode = constantNodes[outerClassInfoIndex - 1];
            formatter.format("outerClass\t%s", outerConstantNode.getValue());
        }
        stringBuilder.append(formatter).append("\n");
    }
}
