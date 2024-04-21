package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ExceptionNode;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class CodeAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(CodeAttribute.class);
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte[] code;

    private int exceptionTableLength;

    private ExceptionNode[] exceptionNodes;

    private int attributesCount;

    private Attribute[] attributes;

    private int endIndex;

    public CodeAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        maxStack = ByteUtils.bytesToInt(bc.next(2));
        maxLocals = ByteUtils.bytesToInt(bc.next(2));
        codeLength = ByteUtils.bytesToInt(bc.next(4));
        code = bc.next(codeLength);
        exceptionTableLength = ByteUtils.bytesToInt(bc.next(2));
        exceptionNodes = new ExceptionNode[exceptionTableLength];
        for (int i = 0; i < exceptionNodes.length; i++) {
            exceptionNodes[i] = new ExceptionNode(bc, constantPoolNode);
        }
        attributesCount = ByteUtils.bytesToInt(bc.next(2));
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributes.length; i++) {
            ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
            int i1 = ByteUtils.bytesToInt(bc.next(2));
            ConstantNode constantVisitor = constantVisitors[i1 - 1];
            String value = constantVisitor.getValue();
            attributes[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.info("CodeAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public ExceptionNode[] getExceptionNodes() {
        return exceptionNodes;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public byte[] getCode() {
        return code;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tmaxStack\t%d\tmaxLocals\t%d\n\tcodeLength\t%d\n", maxStack, maxLocals, codeLength);
        formatter.format("\tcodeHex\t%s\n", ByteUtils.toHexString(code));
        formatter.format("\texceptionTableLength\t%d\n", exceptionTableLength);
        stringBuilder.append(formatter);
        for (ExceptionNode exceptionNode : exceptionNodes) {
            exceptionNode.getLog(stringBuilder);
        }
        for (Attribute attributeVisitor : attributes) {
            attributeVisitor.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info(" maxStack:{}，maxLocals：{}, codeLength:{} ", maxStack, maxLocals, codeLength);
        log.info("code:{}, codeString:{},Hex:{}", code, new String(code), ByteUtils.toHexString(code));
        log.info("exceptionTableLength:{}", exceptionTableLength);
        for (ExceptionNode exceptionNode : exceptionNodes) {
            exceptionNode.log(log, true);
        }
        for (Attribute attributeVisitor : attributes) {
            attributeVisitor.log(log, true);
        }
    }

    @Override
    public void log(Logger logger) {
        log(logger, false);
    }
}
