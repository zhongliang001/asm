package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.attribute.AttributeFactory;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.BitUtils;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class AccessFlagsFormatter {

    /**
     * ACC_PUBLIC 		0000 0000 0000 0001
     * ACC_PRIVATE      0000 0000 0000 0010
     * ACC_PROTECTED    0000 0000 0000 0100
     * ACC_STATIC 		0000 0000 0000 1000
     * ACC_FINAL  		0000 0000 0001 0000
     * ACC_SUPER  		0000 0000 0010 0000
     * ACC_SYNCHRONIZED 0000 0000 0010 0000
     * ACC_BRIDGE       0000 0000 0100 0000
     * ACC_TRANSIENT    0000 0000 1000 0000
     * ACC_VARARGS      0000 0000 1000 0000
     * ACC_NATIVE       0000 0001 0000 0000
     * ACC_INTERFACE	0000 0010 0000 0000
     * ACC_ABSTRACT     0000 0100 0000 0000
     * ACC_VOLATILE     0000 0100 0000 0000
     * ACC_STRICT       0000 1000 0000 0000
     * ACC_SYNTHETIC	0001 0000 0000 0000
     * ACC_ANNOTATION	0010 0000 0000 0000
     * ACC_ENUM		    0100 0000 0000 0000
     * ACC_MODULE		1000 0000 0000 0000
     *
     * @param num AccessFlagNum
     * @return AccessFlagString
     */
    public static List<String> getAccessFlag(int num, int type) {
        List<String> list = new ArrayList<>(16);

        if (BitUtils.checkBit(num, 1)) {
            list.add("ACC_PUBLIC");
        }
        if (BitUtils.checkBit(num, 2)) {
            list.add("ACC_PRIVATE");
        }
        if (BitUtils.checkBit(num, 3)) {
            list.add("ACC_PROTECTED");
        }
        if (BitUtils.checkBit(num, 4)) {
            list.add("ACC_STATIC");
        }
        if (BitUtils.checkBit(num, 5)) {
            list.add("ACC_FINAL");
        }
        if (BitUtils.checkBit(num, 6)) {
            if (type == AccessFlagType.CLASS_ACCESS_FLAG) {
                list.add("ACC_SUPER");
            } else if (type == AccessFlagType.METHOD_ACCESS_FLAG) {
                list.add("ACC_SYNCHRONIZED");
            }
        }
        if (BitUtils.checkBit(num, 7)) {
            list.add("ACC_BRIDGE");
        }
        if (BitUtils.checkBit(num, 8)) {
            if (type == AccessFlagType.CLASS_ACCESS_FLAG) {
                list.add("ACC_TRANSIENT");
            } else if (type == AccessFlagType.METHOD_ACCESS_FLAG) {
                list.add("ACC_VARARGS");
            }
        }
        if (BitUtils.checkBit(num, 9)) {
            list.add("ACC_NATIVE");
        }
        if (BitUtils.checkBit(num, 10)) {
            list.add("ACC_INTERFACE");
        }
        if (BitUtils.checkBit(num, 11)) {
            if (type == AccessFlagType.CLASS_ACCESS_FLAG || type == AccessFlagType.METHOD_ACCESS_FLAG) {
                list.add("ACC_ABSTRACT");
            } else if (type == AccessFlagType.FIELD_ACCESS_FLAG) {
                list.add("ACC_VOLATILE");
            }
        }
        if (BitUtils.checkBit(num, 12)) {
            list.add("ACC_STRICT");
        }
        if (BitUtils.checkBit(num, 13)) {
            list.add("ACC_SYNTHETIC");
        }
        if (BitUtils.checkBit(num, 14)) {
            list.add("ACC_ANNOTATION");
        }
        if (BitUtils.checkBit(num, 15)) {
            list.add("ACC_ENUM");
        }
        if (BitUtils.checkBit(num, 16)) {
            list.add("ACC_MODULE");
        }
        return list;
    }

    public static class SourceFile implements ClassNode {

        private int attributesCount;

        private Attribute[] attributeVisitors;

        public SourceFile(ByteContainer bc, ConstantPoolNode constantPoolNode) {
            attributesCount = ByteUtils.bytesToInt(bc.next(2));
            attributeVisitors = new Attribute[attributesCount];
            for (int i = 0; i < attributesCount; i++) {
                ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
                int i1 = ByteUtils.bytesToInt(bc.next(2));
                ConstantNode constantVisitor = constantVisitors[i1 - 1];
                String value = constantVisitor.getValue();
                attributeVisitors[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
            }
        }

        @Override
        public void log(Logger log) {
            log.info("attributesCount:{}", attributesCount);
            for (Attribute attributeVisitor : attributeVisitors) {
                attributeVisitor.log(log, true);
            }
        }

        @Override
        public void accept(Reader reader) {
            reader.read(this);
        }
    }

    public static class MethodItem implements ClassNode {
        private final Logger logger = LoggerFactory.getLogger(Method.class);
        private AccessFlagsVisitor accessFlagsVisitor;
        private int nameIndex;

        private int descriptorIndex;

        private int attributesCount;

        private Attribute[] attributeVisitors;

        public MethodItem(ByteContainer bc, ConstantPoolNode constantPoolNode) {
            accessFlagsVisitor = new AccessFlagsVisitor(bc, AccessFlagType.METHOD_ACCESS_FLAG);
            nameIndex = ByteUtils.bytesToInt(bc.next(2));
            descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
            attributesCount = ByteUtils.bytesToInt(bc.next(2));
            attributeVisitors = new Attribute[attributesCount];
            for (int i = 0; i < attributeVisitors.length; i++) {
                ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
                int i1 = ByteUtils.bytesToInt(bc.next(2));
                ConstantNode constantVisitor = constantVisitors[i1 - 1];
                String value = constantVisitor.getValue();
                attributeVisitors[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
            }

        }

        public void log(Logger log, boolean isParent) {
            Formatter formatter = new Formatter();
            formatter.format("nameIndex:|%03d|,descriptorIndex:|%s|attributesCount:%d", nameIndex, descriptorIndex, attributesCount);
            log.info("{}", formatter);
            for (Attribute attributeVisitor : attributeVisitors) {
                attributeVisitor.log(log, true);
            }
        }

        //@Override
        public void accept() {
            accessFlagsVisitor.accept();
            logger.info("nameIndex:{},descriptorIndex:{}, attributesCount:{}", nameIndex, descriptorIndex, attributesCount);
            for (int i = 0; i < attributeVisitors.length; i++) {
                //     attributeVisitors[i].accept();
            }
        }

        @Override
        public void accept(Reader reader) {

        }
    }

    public static class Method implements ClassNode {

        private final Logger logger = LoggerFactory.getLogger(Method.class);
        private int methodNum;

        private MethodItem[] methodItem;

        public Method(ByteContainer bc, ConstantPoolNode constantPoolNode) {
            methodNum = ByteUtils.bytesToInt(bc.next(2));
            methodItem = new MethodItem[methodNum];
            for (int i = 0; i < methodNum; i++) {
                methodItem[i] = new MethodItem(bc, constantPoolNode);
            }
        }

        @Override
        public void log(Logger log) {
            log.info("methodNum:{}", methodNum);
            for (MethodItem methodItemVisitor : methodItem) {
                methodItemVisitor.log(log, true);
            }
        }

        @Override
        public void accept(Reader reader) {
            reader.read(this);
        }
    }

    public static class Interfaces {
    }

    public static class FieldItem implements ClassNode {
        private final Logger logger = LoggerFactory.getLogger(FieldItem.class);
        private AccessFlagsVisitor accessFlagsVisitor;

        private int nameIndex;

        private int descriptorIndex;

        private int attributesCount;

        private Attribute[] attributeVisitors;

        public FieldItem(ByteContainer bc, ConstantPoolNode constantPoolNode) {
            accessFlagsVisitor = new AccessFlagsVisitor(bc, AccessFlagType.FIELD_ACCESS_FLAG);
            nameIndex = ByteUtils.bytesToInt(bc.next(2));
            descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
            attributesCount = ByteUtils.bytesToInt(bc.next(2));
            attributeVisitors = new Attribute[attributesCount];
            for (int i = 0; i < attributesCount; i++) {
                ConstantNode[] constantVisitors = constantPoolNode.getConstantNodes();
                int i1 = ByteUtils.bytesToInt(bc.next(2));
                ConstantNode constantVisitor = constantVisitors[i1 - 1];
                String value = constantVisitor.getValue();
                attributeVisitors[i] = AttributeFactory.getAttribute(bc, constantPoolNode, value, i1);
            }
        }

        public void log(Logger log, boolean isParent) {
            Formatter formatter = new Formatter();
            formatter.format("nameIndex:%03d|descriptorIndex:%03d|，attributesCount:%03d|", nameIndex, descriptorIndex, attributesCount);
            log.info("{}", formatter);
            for (Attribute attributeVisitor : attributeVisitors) {
                attributeVisitor.log(log, true);
            }
        }

        @Override
        public void accept(Reader reader) {
            reader.read(this);
            for (Attribute attributeVisitor : attributeVisitors) {
                attributeVisitor.accept(reader);
            }
        }
    }

    public static class Field implements ClassNode {
        private final Logger logger = LoggerFactory.getLogger(Field.class);
        private int fieldsCount;

        private FieldItem[] fieldItemVisitors;

        public Field(ByteContainer bc, ConstantPoolNode constantPoolNode) {
            this.fieldsCount = ByteUtils.bytesToInt(bc.next(2));
            fieldItemVisitors = new FieldItem[this.fieldsCount];
            for (int i = 0; i < fieldItemVisitors.length; i++) {
                fieldItemVisitors[i] = new FieldItem(bc, constantPoolNode);
            }
            if (logger.isDebugEnabled()) {
                log(logger);
            }
        }

        @Override
        public void log(Logger log) {
            log.info("fieldsCount:{}", fieldsCount);
            for (int i = 0; i < fieldItemVisitors.length; i++) {
                fieldItemVisitors[i].log(log, true);
            }
        }

        @Override
        public void accept(Reader reader) {
            reader.read(this);
        }
    }

    public static class ConstantKind {

        public final static int CONSTANT_Utf8 = 1;

        public final static int CONSTANT_Integer = 3;
        public final static int CONSTANT_Class = 7;

        public final static int CONSTANT_Methodref = 10;

        public final static int CONSTANT_NameAndType = 12;


    }

    public static class ExceptionVisitor implements ClassNode {

        private final Logger logger = LoggerFactory.getLogger(ExceptionVisitor.class);

        private int startPc;

        private int endPc;

        private int handlerPc;

        private int catchType;

        public ExceptionVisitor(ByteContainer bc) {
            //        super(bc);
            startPc = ByteUtils.bytesToInt(bc.next(2));
            endPc = ByteUtils.bytesToInt(bc.next(2));
            handlerPc = ByteUtils.bytesToInt(bc.next(2));
            catchType = ByteUtils.bytesToInt(bc.next(2));
        }

        // @Override
        public void accept() {
            logger.info("startPc:{}, endPc:{}，handlerPc：{}，catchType：{}", startPc, endPc, handlerPc, catchType);
        }

        @Override
        public void accept(Reader reader) {

        }
    }
}
