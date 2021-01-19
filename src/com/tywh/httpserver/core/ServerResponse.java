package com.tywh.httpserver.core;

import com.sun.javax.servlet.ServletResponse;

import java.io.PrintWriter;

public class ServerResponse implements ServletResponse {

    private PrintWriter printWriter;

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
}
