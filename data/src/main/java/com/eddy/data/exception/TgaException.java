package com.eddy.data.exception;

import java.io.IOException;

/**
 * Created by liujun on 16-5-24.
 */
public class TgaException extends IOException {

    public static final int ERROR_REQ = 0;
    public static final int ERROR_PARSE = 1;
    public static final int ERROR_LOING = 2;


    private String url;
    private int type;
    private int code;
    private String msg;

    private TgaException() {
    }

    public static class Builder {

        private String url;
        private int type;
        private int code;
        private String msg;

        public  Builder(int type) {
            this(type, null);
        }
        public  Builder(int type,String url) {
            this.type = type;
            this.url = url;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public  Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public TgaException build() {
            TgaException tgaException = new TgaException();
            tgaException.code = code;
            tgaException.type = type;
            tgaException.url = url;
            tgaException.msg = msg;
            return tgaException;
        }

    }

    @Override
    public String getMessage() {
        return msg;
    }
}
