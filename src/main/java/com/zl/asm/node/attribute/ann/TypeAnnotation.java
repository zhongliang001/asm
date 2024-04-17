package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.LocalVarTarget;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Formatter;

public class TypeAnnotation {

    private final Logger logger = LoggerFactory.getLogger(TypeAnnotation.class);
    private String targetType;

    private int typeParameterTarget;

    private int supertypeTarget;

    private TypeParameterBoundTarget typeParameterBoundTarget;

    private int formalParameterTarget;

    private int throwsTarget;

    private LocalVarTarget localVarTarget;

    private int catchTarget;

    private int offsetTarget;

    private TypeArgumentTarget typeArgumentTarget;

    private TypePath typePath;

    private int typeIndex;

    private int numElementValuePairs;

    private ElementValuePair[] elementValuePairs;

    private int startIndex;

    private int endIndex;

    public TypeAnnotation(ByteContainer bc) {
        startIndex = bc.getIndex();
        targetType = ByteUtils.toHexString(bc.next(1));
        if ("00".equals(targetType) || "01".equals(targetType)) {
            typeParameterTarget = ByteUtils.byteToUnsignedInt(bc.next());
        } else if ("10".equals(targetType)) {
            supertypeTarget = ByteUtils.bytesToInt(bc.next(2));
        } else if ("11".equals(targetType) || "12".equals(targetType)) {
            typeParameterBoundTarget = new TypeParameterBoundTarget(bc);
        } else if ("16".equals(targetType)) {
            formalParameterTarget = ByteUtils.byteToUnsignedInt(bc.next());
        } else if ("17".equals(targetType)) {
            throwsTarget = ByteUtils.bytesToInt(bc.next(2));
        } else if ("40".equals(targetType) || "41".equals(targetType)) {
            localVarTarget = new LocalVarTarget(bc);
        } else if ("42".equals(targetType)) {
            catchTarget = ByteUtils.bytesToInt(bc.next(2));
        } else if (Arrays.asList(new String[]{"43", "44", "45", "46"}).contains(targetType)) {
            offsetTarget = ByteUtils.bytesToInt(bc.next(2));
        } else if (Arrays.asList(new String[]{"47", "48", "49", "4A", "4B"}).contains(targetType)) {
            typeArgumentTarget = new TypeArgumentTarget(bc);
        }
        //else if("13".equals(targetType)||"14".equals(targetType)||"15".equals(targetType)){

        // }

        typePath = new TypePath(bc);
        typeIndex = ByteUtils.bytesToInt(bc.next(2));

        numElementValuePairs = ByteUtils.bytesToInt(bc.next(2));
        elementValuePairs = new ElementValuePair[numElementValuePairs];
        for (int i = 0; i < elementValuePairs.length; i++) {
            elementValuePairs[i] = new ElementValuePair(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("TypeAnnotation code: {}", bc.copy(startIndex, endIndex));
        }

    }

    public String getTargetType() {
        return targetType;
    }

    public int getTypeParameterTarget() {
        return typeParameterTarget;
    }

    public int getSupertypeTarget() {
        return supertypeTarget;
    }

    public TypeParameterBoundTarget getTypeParameterBoundTarget() {
        return typeParameterBoundTarget;
    }

    public int getFormalParameterTarget() {
        return formalParameterTarget;
    }

    public int getThrowsTarget() {
        return throwsTarget;
    }

    public LocalVarTarget getLocalVarTarget() {
        return localVarTarget;
    }

    public int getCatchTarget() {
        return catchTarget;
    }

    public int getOffsetTarget() {
        return offsetTarget;
    }

    public TypeArgumentTarget getTypeArgumentTarget() {
        return typeArgumentTarget;
    }

    public TypePath getTypePath() {
        return typePath;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public int getNumElementValuePairs() {
        return numElementValuePairs;
    }

    public ElementValuePair[] getElementValuePairs() {
        return elementValuePairs;
    }

    public void log(Logger log, boolean isParent) {
        if ("00".equals(targetType) || "01".equals(targetType)) {
            log.info("typeParameterTarget: {}", typeParameterTarget);
        } else if ("10".equals(targetType)) {
            log.info("supertypeTarget: {}", supertypeTarget);
        } else if ("11".equals(targetType) || "12".equals(targetType)) {
            typeParameterBoundTarget.log(log, true);
        } else if ("16".equals(targetType)) {
            log.info("formalParameterTarget: {}", formalParameterTarget);
        } else if ("17".equals(targetType)) {
            log.info("throwsTarget: {}", throwsTarget);
        } else if ("40".equals(targetType) || "41".equals(targetType)) {
            localVarTarget.log(log, true);
        } else if ("42".equals(targetType)) {
            log.info("catchTarget: {}", catchTarget);
        } else if (Arrays.asList(new String[]{"43", "44", "45", "46"}).contains(targetType)) {
            log.info("offsetTarget: {}", offsetTarget);
        } else if (Arrays.asList(new String[]{"47", "48", "49", "4A", "4B"}).contains(targetType)) {
            typeArgumentTarget.log(log, true);
        }
        typePath.log(log, true);
        Formatter formatter = new Formatter();
        formatter.format("typeIndex: |%03d|", typeIndex);
        log.info("{},numElementValuePairs: {}", formatter, numElementValuePairs);
        for (ElementValuePair elementValuePair : elementValuePairs) {
            elementValuePair.log(log, true);
        }
    }
}
