package com.snupy.serverpractice;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by goznauk on 9/9/16.
 */
public class Reactor {
    private ServerSocket serverSocket;
    private HandleMap handleMap;

    public Reactor(int port) {
        handleMap = new HandleMap();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        Dispatcher dispatcher = new Dispatcher();

        while (true) {
            dispatcher.dispatch(serverSocket, handleMap);
        }
    }

    public void registerHandler(EventHandler handler) {
        handleMap.put(handler.getHandler(), handler);
    }

    public void removeHandler(EventHandler handler) {
        handleMap.remove(handler.getHandler());
    }

}
