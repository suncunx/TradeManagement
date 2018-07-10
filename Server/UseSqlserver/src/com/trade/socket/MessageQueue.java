package com.trade.socket;


import java.util.concurrent.LinkedBlockingQueue;

import com.trade.socket.bean.MsgBean;


public class MessageQueue {

    private static class InstanceHolder{
        private static final MessageQueue INSTANCE = new MessageQueue();
    }

    private LinkedBlockingQueue<MsgBean> queue = new LinkedBlockingQueue<MsgBean>();

    public static MessageQueue getIns(){
        return InstanceHolder.INSTANCE;
    }

    public MessageQueue offer(MsgBean msgBean){
        //ignore whatever method returned
        queue.offer(msgBean);
        return this;
    }

    public MsgBean take(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public void clear(){
        queue.clear();
    }
}
