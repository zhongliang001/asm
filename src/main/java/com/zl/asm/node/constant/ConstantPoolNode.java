package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlagsFormatter;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.*;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstantPoolNode implements ClassNode {

    private Logger logger = LoggerFactory.getLogger(ConstantPoolNode.class);
    private int count;

    private ConstantNode[] constantNodes;

    public ConstantNode[] getConstantNodes() {
        return constantNodes;
    }

    public void setConstantNodes(ConstantNode[] constantNodes) {
        this.constantNodes = constantNodes;
    }

    public ConstantPoolNode(ByteContainer bc) {
        byte[] next = bc.next(2);
        count = ByteUtils.bytesToInt(next);
        constantNodes = new ConstantNode[count-1];
        for (int i = 1; i < count; i++) {
            byte tag = bc.next();
            switch (tag) {
                case AccessFlagsFormatter.ConstantKind.CONSTANT_Utf8:
                    constantNodes[i-1] = new Utf8Constant(bc, AccessFlagsFormatter.ConstantKind.CONSTANT_Utf8, i);
                    break;
                case AccessFlagsFormatter.ConstantKind.CONSTANT_Integer:
                    constantNodes[i-1] = new IntegerConstant(bc, AccessFlagsFormatter.ConstantKind.CONSTANT_Integer, i);
                    break;
                case AccessFlagsFormatter.ConstantKind.CONSTANT_Methodref:
                    constantNodes[i-1] = new MethodConstant(bc, AccessFlagsFormatter.ConstantKind.CONSTANT_Methodref, i);
                    break;
                case AccessFlagsFormatter.ConstantKind.CONSTANT_Class:
                    constantNodes[i-1] = new ClassConstant(bc, AccessFlagsFormatter.ConstantKind.CONSTANT_Class, i);
                    break;
                case AccessFlagsFormatter.ConstantKind.CONSTANT_NameAndType:
                    constantNodes[i-1] = new NameAndTypeConstant(bc, AccessFlagsFormatter.ConstantKind.CONSTANT_NameAndType, i);
                default:
                    break;
            }
        }

    }

    @Override
    public void log(Logger log) {
        log.info("常量池大小{}", count);
        for (ConstantNode constantNode : constantNodes) {
            if(constantNode !=null){
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
}
