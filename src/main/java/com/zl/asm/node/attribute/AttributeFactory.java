package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.ann.*;
import com.zl.asm.node.constant.ConstantPoolNode;

public class AttributeFactory {

    public static Attribute getAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, String type, int attributeNameIndex) {
        switch (type) {
            case AttributeType.CONSTANT_VALUE:
                return new ConstantAttribute(bc, attributeNameIndex);
            case AttributeType.CODE:
                return new CodeAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.LINENUMBERTABLE:
                return new LineNumberTableAttribute(bc, attributeNameIndex);
            case AttributeType.LOCALVARIABLETABLE:
                return new LocalVariableTableAttribute(bc, attributeNameIndex);
            case AttributeType.SOURCEFILE:
                return new SourceFileAttribute(bc, attributeNameIndex);
            case AttributeType.LOCALVARIABLETYPETABLE:
                return new LocalVariableTypeTableAttribute(bc, attributeNameIndex);
            case AttributeType.STACKMAPTABLE:
                return new StackMapTableAttribute(bc, attributeNameIndex);
            case AttributeType.SIGNATURE:
                return new SignatureAttribute(bc, attributeNameIndex);
            case AttributeType.EXCEPTIONS:
                return new ExceptionAttribute(bc, attributeNameIndex);
            case AttributeType.NESTMEMBERS:
                return new NestMemberAttribute(bc, attributeNameIndex);
            case AttributeType.INNERCLASSES:
                return new InnerClassAttribute(bc, attributeNameIndex);
            case AttributeType.BOOTSTRAPMETHODS:
                return new BootstrapMethodAttribute(bc, attributeNameIndex);
            case AttributeType.NESTHOST:
                return new NestHostAttribute(bc, attributeNameIndex);
            case AttributeType.ENCLOSINGMETHOD:
                return new EnclosingMethodAttribute(bc, attributeNameIndex);
            case AttributeType.MODULE:
                return new ModuleAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEVISIBLEANNOTATIONS:
                return new RuntimeVisibleAnnotationAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEVISIBLEPARAMETERANNOTATIONS:
                return new RuntimeVisibleParameterAnnAttribute(bc, attributeNameIndex);
            case AttributeType.ANNOTATIONDEFAULT:
                return new AnnotationDefaultAttribute(bc, attributeNameIndex);
            case AttributeType.SYNTHETIC:
                return new SyntheticAttribute(bc, attributeNameIndex);
            case AttributeType.DEPRECATED:
                return new DeprecatedAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEINVISIBLEANNOTATIONS:
                return new RuntimeInvisibleAnnotationsAttribute(bc, attributeNameIndex);
            case AttributeType.SOURCEDEBUGEXTENSION:
                return new SourceDebugExtensionAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEINVISIBLEPARAMETERANNOTATIONS:
                return new RuntimeInvisibleParameterAnnAttribute(bc, attributeNameIndex);
            case AttributeType.METHODPARAMETERS:
                return new MethodParametersAttribute(bc, attributeNameIndex);
            case AttributeType.RECORD:
                return new RecordAttribute(bc, constantPoolNode, attributeNameIndex);
            case AttributeType.RUNTIMEVISIBLETYPEANNOTATIONS:
                return new RuntimeVisibleTypeAnnAttribute(bc, attributeNameIndex);
            case AttributeType.RUNTIMEINVISIBLETYPEANNOTATIONS:
                return new RuntimeInvisibleTypeAnnAttribute(bc, attributeNameIndex);
            case AttributeType.PERMITTEDSUBCLASSES:
                return new PermittedSubclassAttribute(bc, attributeNameIndex);
            default:
                return new UnkownAttribute(bc, attributeNameIndex);
        }
    }
}
