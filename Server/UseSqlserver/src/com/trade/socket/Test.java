package com.trade.socket;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


public class Test {
	public static ConcurrentHashMap<String, Socket> deliverSockets = new ConcurrentHashMap<String, Socket>();
    public static ConcurrentHashMap<String, Socket> userSockets = new ConcurrentHashMap<String, Socket>();
}
