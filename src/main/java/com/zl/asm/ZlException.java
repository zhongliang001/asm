package com.zl.asm;

public class ZlException extends RuntimeException {
    private String msg;

    public ZlException(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }


}
