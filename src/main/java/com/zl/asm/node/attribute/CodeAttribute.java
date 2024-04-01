package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ExceptionNode;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(CodeAttribute.class);
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte[] code;

    private int exceptionTableLength;

    private ExceptionNode[] exceptionNodes;

    private int attributesCount;

    private Attribute[] attributeVisitors;

    public CodeAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        maxStack = ByteUtils.bytesToInt(bc.next(2));
        maxLocals = ByteUtils.bytesToInt(bc.next(2));
        codeLength = ByteUtils.bytesToInt(bc.next(4));
        code = bc.next(codeLength);
        exceptionTableLength = ByteUtils.bytesToInt(bc.next(2));
        exceptionNodes = new ExceptionNode[exceptionTableLength];
        for (int i = 0; i < exceptionNodes.length; i++) {
            exceptionNodes[i] = new ExceptionNode(bc);
        }
        attributesCount = ByteUtils.bytesToInt(bc.next(2));
        attributeVisitors = new Attribute[attributesCount];
        for (int i = 0; i < attributeVisitors.length; i++) {
            ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
            int i1 = ByteUtils.bytesToInt(bc.next(2));
            ConstantNode constantVisitor = constantVisitors[i1 - 1];
            String value = constantVisitor.getValue();
            attributeVisitors[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
        }
        if (logger.isDebugEnabled()) {
            log(logger);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info(" maxStack:{}，maxLocals：{}, codeLength:{} ", maxStack, maxLocals, codeLength);
        log.info("code:{}, codeString:{},Hex:{}", code, new String(code), ByteUtils.toHexString(code));
        log.info("exceptionTableLength:{}", exceptionTableLength);
        if (!isParent) {
            for (Attribute attributeVisitor : attributeVisitors) {
                attributeVisitor.log(log, true);
            }
        }
    }

    @Override
    public void log(Logger logger) {
        log(logger, false);
    }
}
