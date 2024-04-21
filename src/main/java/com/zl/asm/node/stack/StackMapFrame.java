package com.zl.asm.node.stack;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class StackMapFrame {

    private final Logger logger = LoggerFactory.getLogger(StackMapFrame.class);
    private int frameType;

    private int numberOfLocals;

    private VerificationTypeInfo[] locals;

    private int offsetDelta;

    private int numberOfStackItems;

    private VerificationTypeInfo[] stack;

    private String frame;

    private int startIndex;

    private int endIndex;
    private ConstantPoolNode constantPoolNode;

    public StackMapFrame(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        frameType = ByteUtils.byteToUnsignedInt(bc.next());
        if (frameType >= SameFrameType.SAME_MIN && frameType <= SameFrameType.SAME_MAX) {
            frame = "SAME_FRAME";
        } else if (frameType >= SameFrameType.SAME_LOCALS_1_STACK_ITEM_MIN && frameType <= SameFrameType.SAME_LOCALS_1_STACK_ITEM_MAX) {
            numberOfLocals = 1;
            locals = new VerificationTypeInfo[numberOfLocals];
            locals[0] = new VerificationTypeInfo(bc, constantPoolNode);
            frame = "SAME_LOCALS_1_STACK_ITEM";
        } else if (frameType == SameFrameType.SAME_LOCALS_1_STACK_ITEM_EXTENDED) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            numberOfStackItems = 1;
            stack = new VerificationTypeInfo[numberOfStackItems];
            stack[0] = new VerificationTypeInfo(bc, constantPoolNode);
            frame = "SAME_LOCALS_1_STACK_ITEM_EXTENDED";
        } else if (frameType >= SameFrameType.CHOP_MIN && frameType <= SameFrameType.CHOP_MAX) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            frame = "CHOP";
        } else if (frameType == SameFrameType.SAME_FRAME_EXTENDED) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            frame = "SAME_FRAME_EXTENDED";
        } else if (frameType >= SameFrameType.APPEND_MIN && frameType <= SameFrameType.APPEND_MAX) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            numberOfLocals = frameType - 251;
            locals = new VerificationTypeInfo[numberOfLocals];
            for (int i = 0; i < locals.length; i++) {
                locals[i] = new VerificationTypeInfo(bc, constantPoolNode);
            }
            frame = "APPEND";
        } else if (frameType == SameFrameType.FULL_FRAME) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            numberOfLocals = ByteUtils.bytesToInt(bc.next(2));
            locals = new VerificationTypeInfo[numberOfLocals];
            for (int i = 0; i < locals.length; i++) {
                locals[i] = new VerificationTypeInfo(bc, constantPoolNode);
            }
            numberOfStackItems = ByteUtils.bytesToInt(bc.next(2));
            stack = new VerificationTypeInfo[numberOfStackItems];
            for (int i = 0; i < stack.length; i++) {
                stack[i] = new VerificationTypeInfo(bc, constantPoolNode);
            }
            frame = "FULL_FRAME";
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("StackMapFrame code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public void log(Logger logger, boolean isParent) {
        logger.info("frameType:{},offsetDelta:{}, numberOfLocals:{}, numberOfStackItems:{}", frameType, offsetDelta, numberOfLocals, numberOfStackItems);
    }


    public void getLog(StringBuilder stringBuilder) {
        stringBuilder.append("\t").append(frame).append("\n");
        if (numberOfLocals > 0) {
            stringBuilder.append("\t\tnumberOfLocals\t").append(numberOfLocals).append("\n");
            for (VerificationTypeInfo local : locals) {
                local.getLog(stringBuilder);
            }
        }
        Formatter formatter = new Formatter();
        if (frameType == SameFrameType.SAME_LOCALS_1_STACK_ITEM_EXTENDED ||
                (frameType >= SameFrameType.CHOP_MIN && frameType <= SameFrameType.CHOP_MAX) ||
                frameType == SameFrameType.SAME_FRAME_EXTENDED ||
                frameType >= SameFrameType.APPEND_MIN && frameType <= SameFrameType.APPEND_MAX ||
                frameType == SameFrameType.FULL_FRAME
        ) {
            formatter.format("\t\toffsetDelta\t%d\t\n", offsetDelta);
        }
        stringBuilder.append(formatter);

        if (numberOfStackItems > 0) {
            stringBuilder.append("\t\tnumberOfStackItems\t").append(numberOfStackItems).append("\n");
            for (VerificationTypeInfo local : stack) {
                local.getLog(stringBuilder);
            }
        }
        stringBuilder.append("\n");
    }
}
