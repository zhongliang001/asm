package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.node.ConstantKind;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

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
            int tag = ByteUtils.byteToUnsignedInt(bc.next());
            switch (tag) {
                case ConstantKind.CONSTANT_Utf8:
                    try {
                        constantNodes[i - 1] = new Utf8Constant(bc, ConstantKind.CONSTANT_Utf8, i);
                    } catch (NullPointerException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case ConstantKind.CONSTANT_Integer:
                    constantNodes[i - 1] = new IntegerConstant(bc, ConstantKind.CONSTANT_Integer, i);
                    break;
                case ConstantKind.CONSTANT_Fieldref:
                    constantNodes[i - 1] = new FieldConstant(bc, ConstantKind.CONSTANT_Fieldref, i);
                    break;
                case ConstantKind.CONSTANT_Methodref:
                    constantNodes[i - 1] = new MethodConstant(bc, this, ConstantKind.CONSTANT_Methodref, i);
                    break;
                case ConstantKind.CONSTANT_Class:
                    constantNodes[i - 1] = new ClassConstant(bc, this, ConstantKind.CONSTANT_Class, i);
                    break;
                case ConstantKind.CONSTANT_String:
                    constantNodes[i - 1] = new StringConstant(bc, this, ConstantKind.CONSTANT_String, i);
                    break;
                case ConstantKind.CONSTANT_InterfaceMethodref:
                    constantNodes[i - 1] = new InterfaceMethodrefConstant(bc, this, ConstantKind.CONSTANT_InterfaceMethodref, i);
                    break;
                case ConstantKind.CONSTANT_NameAndType:
                    constantNodes[i - 1] = new NameAndTypeConstant(bc, this, ConstantKind.CONSTANT_NameAndType, i);
                    break;
                case ConstantKind.CONSTANT_Dynamic_info:
                    constantNodes[i - 1] = new DynamicConstant(bc, ConstantKind.CONSTANT_Dynamic_info, i);
                    break;
                case ConstantKind.CONSTANT_InvokeDynamic_info:
                    constantNodes[i - 1] = new DynamicConstant(bc, ConstantKind.CONSTANT_InvokeDynamic_info, i);
                    break;
                case ConstantKind.CONSTANT_MethodHandle_info:
                    constantNodes[i - 1] = new MethodHandleConstant(bc, this, ConstantKind.CONSTANT_MethodHandle_info, i);
                    break;
                case ConstantKind.CONSTANT_MethodType_info:
                    constantNodes[i - 1] = new MethodTypeConstant(bc, this, ConstantKind.CONSTANT_MethodType_info, i);
                    break;
                case ConstantKind.CONSTANT_Module_info:
                    constantNodes[i - 1] = new ModuleConstant(bc, this, ConstantKind.CONSTANT_Module_info, i);
                    break;
                case ConstantKind.CONSTANT_Package_info:
                    constantNodes[i - 1] = new PackageConstant(bc, this, ConstantKind.CONSTANT_Package_info, i);
                    break;
                case ConstantKind.CONSTANT_Long:
                    constantNodes[i - 1] = new LongConstant(bc, ConstantKind.CONSTANT_Long, i);
                    break;
                case ConstantKind.CONSTANT_Double:
                    constantNodes[i - 1] = new DoubleConstant(bc, ConstantKind.CONSTANT_Long, i);
                    break;
                case ConstantKind.CONSTANT_Float:
                    constantNodes[i - 1] = new FloatConstant(bc, ConstantKind.CONSTANT_Float, i);
                    break;
                default:
                    throw new RuntimeException(String.valueOf(tag));
            }
            if (tag == ConstantKind.CONSTANT_Long || tag == ConstantKind.CONSTANT_Double) {
                i++;
            }
        }

        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.debug("ConstantPooledNodeCode:{}", bc.copy(startIndex, endIndex));
        }

    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("count:\t\t%d\n", count);
        formatter.format("Constant Pool\n");
        stringBuilder.append(formatter);
        for (ConstantNode constantNode : constantNodes) {
            if (constantNode != null) {
                constantNode.getLog(stringBuilder);
            }
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
    }


    public int getCount() {
        return count;
    }
}
