package org.tywh.oa.servlet;

import com.sun.javax.servlet.Servlet;
import com.sun.javax.servlet.ServletResponse;
import com.tywh.httpserver.core.ServerResponse;

import java.io.PrintWriter;

public class LoginServlet implements Servlet {

    public void service(ServletResponse servletResponse) {
        PrintWriter printWriter = servletResponse.getPrintWriter();
        printWriter.print("<html>");
        printWriter.print("<head>");
        printWriter.print("<meta charset=UTF-8 >");
        printWriter.print("<title>404-´íÎó</title>");
        printWriter.print("</head>");
        printWriter.print("<body>");
        printWriter.print("404-not found ´íÎó");
        printWriter.print("</body>");
        printWriter.print("</html>");
    }
}
