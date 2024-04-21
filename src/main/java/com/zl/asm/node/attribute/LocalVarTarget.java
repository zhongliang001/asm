package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVarTarget {

    private final Logger logger = LoggerFactory.getLogger(LocalVarTarget.class);
    private int tableLength;

    private LocalVarTable[] localVarTables;

    private int startIndex;

    private int endIndex;

    public LocalVarTarget(ByteContainer bc) {
        startIndex = bc.getIndex();
        tableLength = ByteUtils.bytesToInt(bc.next(2));
        localVarTables = new LocalVarTable[tableLength];
        for (int i = 0; i < localVarTables.length; i++) {
            localVarTables[i] = new LocalVarTable(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVarTarget code: {}", bc.copy(startIndex, endIndex));
        }

    }

    public int getTableLength() {
        return tableLength;
    }

    public LocalVarTable[] getLocalVarTables() {
        return localVarTables;
    }

    public void log(Logger log, boolean isParent) {
        log.info("tableLength: {}", tableLength);
        for (LocalVarTable localVarTable : localVarTables) {
            localVarTable.log(log, true);
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\t\t\ttableLength\t%d\n", tableLength);
        stringBuilder.append(formatter);
        for (LocalVarTable localVarTable : localVarTables) {
            localVarTable.getLog(stringBuilder);
        }

    }
}
