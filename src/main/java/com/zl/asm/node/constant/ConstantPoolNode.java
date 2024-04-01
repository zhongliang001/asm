package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.node.ConstantKind;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstantPoolNode implements ClassNode {

    private Logger logger = LoggerFactory.getLogger(ConstantPoolNode.class);
    private int count;

    private int startIndex;
    private int endIndex;

    private ConstantNode[] constantNodes;

    public ConstantNode[] getConstantNodes() {
        return constantNodes;
    }

    public void setConstantNodes(ConstantNode[] constantNodes) {
        this.constantNodes = constantNodes;
    }

    public ConstantPoolNode(ByteContainer bc) {
        startIndex = bc.getIndex();
        byte[] next = bc.next(2);
        count = ByteUtils.bytesToInt(next);
        constantNodes = new ConstantNode[count - 1];
        for (int i = 1; i < count; i++) {
            byte tag = bc.next();
            switch (tag) {
                case ConstantKind.CONSTANT_Utf8:
                    constantNodes[i - 1] = new Utf8Constant(bc, ConstantKind.CONSTANT_Utf8, i);
                    break;
                case ConstantKind.CONSTANT_Integer:
                    constantNodes[i - 1] = new IntegerConstant(bc, ConstantKind.CONSTANT_Integer, i);
                    break;
                case ConstantKind.CONSTANT_Fieldref:
                    constantNodes[i - 1] = new FieldConstant(bc, ConstantKind.CONSTANT_Fieldref, i);
                    break;
                case ConstantKind.CONSTANT_Methodref:
                    constantNodes[i - 1] = new MethodConstant(bc, ConstantKind.CONSTANT_Methodref, i);
                    break;
                case ConstantKind.CONSTANT_Class:
                    constantNodes[i - 1] = new ClassConstant(bc, ConstantKind.CONSTANT_Class, i);
                    break;
                case ConstantKind.CONSTANT_NameAndType:
                    constantNodes[i - 1] = new NameAndTypeConstant(bc, ConstantKind.CONSTANT_NameAndType, i);
                    break;
                default:
                    break;
            }
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.debug("ConstanPooledNodeCode:{}", bc.copy(startIndex, endIndex));
        }

    }

    @Override
    public void log(Logger log) {
        log.info("常量池大小{}", count);
        for (ConstantNode constantNode : constantNodes) {
            if (constantNode != null) {
                constantNode.log(log, true);
            }

        }
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
        for (ConstantNode constantNode : constantNodes) {
            if (constantNode != null) {
                constantNode.accept(reader);
            }
        }
    }


    public int getCount() {
        return count;
    }
}
