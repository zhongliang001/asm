package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.attribute.AttributeFactory;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

import java.util.Formatter;

public class SourceFile implements ClassNode {

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
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("SourceFile attributesCount\t%d\n", attributesCount);
        stringBuilder.append(formatter);
        for (Attribute attributeVisitor : attributeVisitors) {
            attributeVisitor.getLog(stringBuilder);
        }

    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}