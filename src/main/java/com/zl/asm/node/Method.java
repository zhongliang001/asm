package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Method implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Method.class);
    private int methodNum;

    private MethodItem[] methodItem;

    public Method(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        methodNum = ByteUtils.bytesToInt(bc.next(2));
        methodItem = new MethodItem[methodNum];
        for (int i = 0; i < methodNum; i++) {
            methodItem[i] = new MethodItem(bc, constantPoolNode);
        }
    }

    @Override
    public void log(Logger log) {
        log.info("methodNum:{}", methodNum);
        for (MethodItem methodItemVisitor : methodItem) {
            methodItemVisitor.log(log, true);
        }
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
