package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.PathTable;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class TypePath {

    private final Logger logger = LoggerFactory.getLogger(TypePath.class);

    private int pathLength;

    private PathTable[] pathTables;

    private int startIndex;

    private int endIndex;

    public TypePath(ByteContainer bc) {
        startIndex = bc.getIndex();
        pathLength = ByteUtils.byteToUnsignedInt(bc.next());
        pathTables = new PathTable[pathLength];
        for (int i = 0; i < pathTables.length; i++) {
            pathTables[i] = new PathTable(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("TypePath code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getPathLength() {
        return pathLength;
    }

    public PathTable[] getPathTables() {
        return pathTables;
    }

    public void log(Logger log, boolean isParent) {
        log.info("pathLength: {}", pathLength);
        for (PathTable pathTable : pathTables) {
            pathTable.log(log, isParent);
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\t\t\t\tpathLength\t%d\n", pathLength);
        stringBuilder.append(formatter);
        for (PathTable pathTable : pathTables) {
            pathTable.getLog(stringBuilder);
        }
    }
}
