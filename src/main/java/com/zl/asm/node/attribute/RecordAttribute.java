package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(RecordAttribute.class);
    private int componentCount;

    private RecordComponentInfo[] recordComponentInfos;

    private int endIndex;

    public RecordAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        componentCount = ByteUtils.bytesToInt(bc.next(2));
        recordComponentInfos = new RecordComponentInfo[componentCount];
        for (int i = 0; i < recordComponentInfos.length; i++) {
            recordComponentInfos[i] = new RecordComponentInfo(bc, constantPoolNode);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("RecordAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getComponentCount() {
        return componentCount;
    }

    public RecordComponentInfo[] getRecordComponentInfos() {
        return recordComponentInfos;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("componentCount: {}", componentCount);
        for (RecordComponentInfo recordComponentInfo : recordComponentInfos) {
            recordComponentInfo.log(log, isParent);
        }
    }
}
