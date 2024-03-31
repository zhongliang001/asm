package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlagsFormatter;
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

    private AccessFlagsFormatter.ExceptionVisitor[] exceptionVisitors;

    private int attributesCount;

    private Attribute[] attributeVisitors;

    @Override
    public void log(Logger logger, boolean isParent) {
        logger.info(" maxStack:{}，maxLocals：{}, codeLength:{} ", maxStack, maxLocals, codeLength);
        logger.info("code:{}, codeString:{},Hex:{}", code,new String(code), ByteUtils.toHexString(code));
        logger.info("exceptionTableLength:{}", exceptionTableLength);
        for (Attribute attributeVisitor : attributeVisitors) {
            attributeVisitor.log(logger, true);
        }
    }

    public CodeAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        maxStack = ByteUtils.bytesToInt(bc.next(2));
        maxLocals = ByteUtils.bytesToInt(bc.next(2));
        codeLength = ByteUtils.bytesToInt(bc.next(4));
             code = bc.next(codeLength);
        exceptionTableLength = ByteUtils.bytesToInt(bc.next(2));
        exceptionVisitors = new AccessFlagsFormatter.ExceptionVisitor[exceptionTableLength];
        for (int i = 0; i < exceptionVisitors.length; i++) {
            exceptionVisitors[i] = new AccessFlagsFormatter.ExceptionVisitor(bc);
        }
        attributesCount = ByteUtils.bytesToInt(bc.next(2));
        attributeVisitors = new Attribute[attributesCount];
        for (int i = 0; i < attributeVisitors.length; i++) {
            ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
            int i1 = ByteUtils.bytesToInt(bc.next(2));
            ConstantNode constantVisitor = constantVisitors[i1-1];
            String value = constantVisitor.getValue();
            attributeVisitors[i] = AttributeFactory.getAttribute(bc,constantPoolNode,value,i1 );
        }
    }


    public void accept() {
      //  super.accept();
        logger.info(" maxStack:{}，maxLocals：{}, codeLength:{} ", maxStack, maxLocals, codeLength);
        logger.info("code:{}, codeString:{}", code,new String(code));
        logger.info("exceptionTableLength:{}", exceptionTableLength);
        for (int i = 0; i < exceptionVisitors.length; i++) {
            exceptionVisitors[i].accept();
        }
        for (int i = 0; i < attributeVisitors.length; i++) {
       //     attributeVisitors[i].accept();
        }
    }

    @Override
    public void log(Logger logger) {
        super.log(logger);
    }
}
