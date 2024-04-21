package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.ann.*;
import com.zl.asm.node.constant.ConstantPoolNode;

public class AttributeFactory {

    public static Attribute getAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, String type, int attributeNameIndex) {
        switch (type) {
            case AttributeType.CONSTANT_VALUE:
                return new ConstantAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.CODE:
                return new CodeAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.LINENUMBERTABLE:
                return new LineNumberTableAttribute(bc, attributeNameIndex);
            case AttributeType.LOCALVARIABLETABLE:
                return new LocalVariableTableAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.SOURCEFILE:
                return new SourceFileAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.LOCALVARIABLETYPETABLE:
                return new LocalVariableTypeTableAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.STACKMAPTABLE:
                return new StackMapTableAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.SIGNATURE:
                return new SignatureAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.EXCEPTIONS:
                return new ExceptionAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.NESTMEMBERS:
                return new NestMemberAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.INNERCLASSES:
                return new InnerClassAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.BOOTSTRAPMETHODS:
                return new BootstrapMethodAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.NESTHOST:
                return new NestHostAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.ENCLOSINGMETHOD:
                return new EnclosingMethodAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.MODULE:
                return new ModuleAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.RUNTIMEVISIBLEANNOTATIONS:
                return new RuntimeVisibleAnnotationAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.RUNTIMEVISIBLEPARAMETERANNOTATIONS:
                return new RuntimeVisibleParameterAnnAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.ANNOTATIONDEFAULT:
                return new AnnotationDefaultAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.SYNTHETIC:
                return new SyntheticAttribute(bc, attributeNameIndex);
            case AttributeType.DEPRECATED:
                return new DeprecatedAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEINVISIBLEANNOTATIONS:
                return new RuntimeInvisibleAnnotationsAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.SOURCEDEBUGEXTENSION:
                return new SourceDebugExtensionAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEINVISIBLEPARAMETERANNOTATIONS:
                return new RuntimeInvisibleParameterAnnAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.METHODPARAMETERS:
                return new MethodParametersAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.RECORD:
                return new RecordAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.RUNTIMEVISIBLETYPEANNOTATIONS:
                return new RuntimeVisibleTypeAnnAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.RUNTIMEINVISIBLETYPEANNOTATIONS:
                return new RuntimeInvisibleTypeAnnAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.PERMITTEDSUBCLASSES:
                return new PermittedSubclassAttribute(bc, attributeNameIndex);
            default:
                return new UnkownAttribute(bc, attributeNameIndex);
        }
    }
}
