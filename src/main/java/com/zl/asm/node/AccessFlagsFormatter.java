package com.zl.asm.node;

import com.zl.asm.util.BitUtils;

import java.util.ArrayList;
import java.util.List;

public class AccessFlagsFormatter {

    /**
     * ACC_PUBLIC 		0000 0000 0000 0001
     * ACC_PRIVATE      0000 0000 0000 0010
     * ACC_PROTECTED    0000 0000 0000 0100
     * ACC_STATIC 		0000 0000 0000 1000
     * ACC_FINAL  		0000 0000 0001 0000
     * ACC_SUPER  		0000 0000 0010 0000
     * ACC_TRANSITIVE   0000 0000 0010 0000
     * ACC_OPEN         0000 0000 0010 0000
     * ACC_SYNCHRONIZED 0000 0000 0010 0000
     * ACC_BRIDGE       0000 0000 0100 0000
     * ACC_STATIC_PHASE 0000 0000 0100 0000
     * ACC_TRANSIENT    0000 0000 1000 0000
     * ACC_VARARGS      0000 0000 1000 0000
     * ACC_NATIVE       0000 0001 0000 0000
     * ACC_INTERFACE	0000 0010 0000 0000
     * ACC_ABSTRACT     0000 0100 0000 0000
     * ACC_VOLATILE     0000 0100 0000 0000
     * ACC_STRICT       0000 1000 0000 0000
     * ACC_SYNTHETIC	0001 0000 0000 0000
     * ACC_ANNOTATION	0010 0000 0000 0000
     * ACC_ENUM		    0100 0000 0000 0000
     * ACC_MODULE		1000 0000 0000 0000
     * ACC_MANDATED     1000 0000 0000 0000
     *
     * @param num AccessFlagNum
     * @return AccessFlagString
     */
    public static List<String> getAccessFlag(int num, int type) {
        List<String> list = new ArrayList<>(16);

        if (BitUtils.checkBit(num, 1)) {
            list.add("ACC_PUBLIC");
        }
        if (BitUtils.checkBit(num, 2)) {
            list.add("ACC_PRIVATE");
        }
        if (BitUtils.checkBit(num, 3)) {
            list.add("ACC_PROTECTED");
        }
        if (BitUtils.checkBit(num, 4)) {
            list.add("ACC_STATIC");
        }
        if (BitUtils.checkBit(num, 5)) {
            list.add("ACC_FINAL");
        }
        if (BitUtils.checkBit(num, 6)) {
            if (type == AccessFlagType.CLASS_ACCESS_FLAG) {
                list.add("ACC_SUPER");
            } else if (type == AccessFlagType.METHOD_ACCESS_FLAG) {
                list.add("ACC_SYNCHRONIZED");
            } else if (type == AccessFlagType.MODULE_ACCESS_FLAG_1) {
                list.add("ACC_TRANSITIVE");
            } else if (type == AccessFlagType.MODULE_ACCESS_FLAG) {
                list.add("ACC_OPEN");
            }
        }
        if (BitUtils.checkBit(num, 7)) {
            if (type == AccessFlagType.MODULE_ACCESS_FLAG_1) {
                list.add("ACC_STATIC_PHASE");
            } else {
                list.add("ACC_BRIDGE");
            }

        }
        if (BitUtils.checkBit(num, 8)) {
            if (type == AccessFlagType.CLASS_ACCESS_FLAG) {
                list.add("ACC_TRANSIENT");
            } else if (type == AccessFlagType.METHOD_ACCESS_FLAG) {
                list.add("ACC_VARARGS");
            }
        }
        if (BitUtils.checkBit(num, 9)) {
            list.add("ACC_NATIVE");
        }
        if (BitUtils.checkBit(num, 10)) {
            list.add("ACC_INTERFACE");
        }
        if (BitUtils.checkBit(num, 11)) {
            if (type == AccessFlagType.CLASS_ACCESS_FLAG || type == AccessFlagType.METHOD_ACCESS_FLAG) {
                list.add("ACC_ABSTRACT");
            } else if (type == AccessFlagType.FIELD_ACCESS_FLAG) {
                list.add("ACC_VOLATILE");
            }
        }
        if (BitUtils.checkBit(num, 12)) {
            list.add("ACC_STRICT");
        }
        if (BitUtils.checkBit(num, 13)) {
            list.add("ACC_SYNTHETIC");
        }
        if (BitUtils.checkBit(num, 14)) {
            list.add("ACC_ANNOTATION");
        }
        if (BitUtils.checkBit(num, 15)) {
            list.add("ACC_ENUM");
        }
        if (BitUtils.checkBit(num, 16)) {
            if (type == AccessFlagType.MODULE_ACCESS_FLAG || type == AccessFlagType.MODULE_ACCESS_FLAG_1) {
                list.add("ACC_MANDATED");
            } else {
                list.add("ACC_MODULE");
            }

        }
        return list;
    }

}
