package com.zl.asm.node.stack;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackMapFrame implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(StackMapFrame.class);
    private int frameType;

    private VerificationTypeInfo[] locals;

    private int offsetDelta;

    private int numberOfLocals;

    private int numberOfStackItems;

    private VerificationTypeInfo[] stack;

    private String frame;

    private int startIndex;

    private int endIndex;

    public StackMapFrame(ByteContainer bc) {
        startIndex = bc.getIndex();
        frameType = ByteUtils.byteToUnsignedInt(bc.next());
        if (frameType >= SameFrameType.SAME_MIN && frameType <= SameFrameType.SAME_MAX) {
            frame = "SAME_FRAME";
        } else if (frameType >= SameFrameType.SAME_LOCALS_1_STACK_ITEM_MIN && frameType <= SameFrameType.SAME_LOCALS_1_STACK_ITEM_MAX) {
            numberOfLocals = 1;
            locals = new VerificationTypeInfo[numberOfLocals];
            locals[0] = new VerificationTypeInfo(bc);
            frame = "SAME_LOCALS_1_STACK_ITEM";
        } else if (frameType == SameFrameType.SAME_LOCALS_1_STACK_ITEM_EXTENDED) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            numberOfStackItems = 1;
            stack = new VerificationTypeInfo[numberOfStackItems];
            stack[0] = new VerificationTypeInfo(bc);
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
                locals[i] = new VerificationTypeInfo(bc);
            }
            frame = "APPEND";
        } else if (frameType == SameFrameType.FULL_FRAME) {
            offsetDelta = ByteUtils.bytesToInt(bc.next(2));
            numberOfLocals = ByteUtils.bytesToInt(bc.next(2));
            locals = new VerificationTypeInfo[numberOfLocals];
            for (int i = 0; i < locals.length; i++) {
                locals[i] = new VerificationTypeInfo(bc);
            }
            numberOfStackItems = ByteUtils.bytesToInt(bc.next(2));
            stack = new VerificationTypeInfo[numberOfStackItems];
            for (int i = 0; i < stack.length; i++) {
                stack[i] = new VerificationTypeInfo(bc);
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

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
