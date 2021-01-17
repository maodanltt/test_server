package com.tywh.httpserver.core;

import com.tywh.httpserver.util.Logger;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable{

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
//        PrintStream ps = null;
        try {
            Logger.writeLog("Thread name :" + Thread.currentThread().getName());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            String requestURI = br.readLine().split(" ")[1];
            if (requestURI.endsWith(".html") || requestURI.endsWith(".htm")) {
                responseStaticResource(pw,requestURI);
                pw.flush();
            } else {

            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void responseStaticResource(PrintWriter pw, String URI) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("test_server" + URI)));
            String temp = null;
            StringBuilder html = new StringBuilder();
            html.append("HTTP/1.1 200 ok\n");
            html.append("Content-Type: text/html;charset=utf-8\n\n");
            while((temp = br.readLine()) != null) {
                html.append(temp);
            }
            pw.print(html);
        } catch (FileNotFoundException e) {
            StringBuilder html = new StringBuilder();
            html.append("HTTP/1.1 404 Not Found\n");
            html.append("Content-Type: text/html;charset=utf-8\n\n");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset=UTF-8 >");
            html.append("<title>404-´íÎó</title>");
            html.append("</head>");
            html.append("<body>");
            html.append("404-not found ´íÎó");
            html.append("</body>");
            html.append("</html>");
            pw.print(html);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
