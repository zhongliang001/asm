package com.zl.asm.node;

import com.zl.asm.reader.Reader;
import org.slf4j.Logger;

public interface ClassNode {


    default void log(Logger logger) {

    }

    default void getLog(StringBuilder stringBuilder) {
    }

    void accept(Reader reader);
}
