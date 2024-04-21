package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Method implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Method.class);
    private int methodNum;

    private MethodItem[] methodItem;

    private int startIndex;

    private int endIndex;

    public Method(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.startIndex = bc.getIndex();
        this.methodNum = ByteUtils.bytesToInt(bc.next(2));
        this.methodItem = new MethodItem[this.methodNum];
        for (int i = 0; i < this.methodNum; i++) {
            methodItem[i] = new MethodItem(bc, constantPoolNode);
        }
        this.endIndex = bc.getIndex() - 1;

        if (logger.isDebugEnabled()) {
            log(logger);
            logger.info("Method code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getMethodNum() {
        return methodNum;
    }

    public MethodItem[] getMethodItem() {
        return methodItem;
    }

    @Override
    public void log(Logger log) {
        log.info("methodNum:{}", methodNum);
        for (MethodItem methodItemVisitor : methodItem) {
            methodItemVisitor.log(log, true);
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("methodNum\t%d\n", methodNum);
        stringBuilder.append(formatter);
        int i = 1;
        for (MethodItem item : methodItem) {
            stringBuilder.append("Method:").append(i++).append("\n");
            item.getLog(stringBuilder);
        }
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
