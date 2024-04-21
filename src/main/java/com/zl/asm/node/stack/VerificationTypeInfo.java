package com.zl.asm.node.stack;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;

public class VerificationTypeInfo {
    private final int tag;

    private int cpoolIndex;

    private int offset;

    private final ConstantPoolNode constantPoolNode;

    public VerificationTypeInfo(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        tag = ByteUtils.byteToUnsignedInt(bc.next());
        if (tag == 7) {
            cpoolIndex = ByteUtils.bytesToInt(bc.next(2));
        }
        if (tag == 8) {
            offset = ByteUtils.bytesToInt(bc.next(2));
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        stringBuilder.append("\t\t\t");
        switch (tag) {
            case 0:
                stringBuilder.append("ITEM_Top");
                break;
            case 1:
                stringBuilder.append("ITEM_Integer");
                break;
            case 2:
                stringBuilder.append("ITEM_Float");
                break;
            case 5:
                stringBuilder.append("ITEM_Null");
                break;
            case 6:
                stringBuilder.append("ITEM_UninitializedThis");
                break;
            case 7:
                ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
                ConstantNode constantNode = constantNodes[cpoolIndex - 1];
                stringBuilder.append(constantNode.getValue()).append("\t").append("ITEM_Object");
                break;
            case 8:
                stringBuilder.append(offset).append("\t").append("ITEM_Uninitialized");
                break;
            default:
                stringBuilder.append(tag);
        }
    }
}
