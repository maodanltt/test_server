package com.tywh.httpserver.core;

import com.tywh.httpserver.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * httpserver入口类
 * @author tywh
 * @version 1.0
 * @since 1.0
 */
public class ServerLaunch {
    /**
     * 主入口
     * @param args
     */
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader br = null;
        try {
            Logger.writeLog("please wait for seconds, server is starting....");
            long fromTime = System.currentTimeMillis();
            WebParser.parse(new String[]{"oa"});
            serverSocket = new ServerSocket(ServerParser.getServerPort());
            Logger.writeLog("server port : " + ServerParser.getServerPort());
            long endTime = System.currentTimeMillis();
            Logger.writeLog("server has started in " + (endTime - fromTime) + "ms" );
            while(true) {
                socket= serverSocket.accept();
//                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String readData = null;
//                while((readData = br.readLine()) != null) {
//                    System.out.println(readData);
//                }
                Thread newThread= new Thread(new RequestHandler(socket));
                newThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (socket != null) {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
