package com.study.basics.socket;

import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * UDP网络编程：
 * ✔ 将数据、源、目的封装成数据包，不需要建立连接
 * ✔ 每个数据报的大小限制在64K内
 * ✔ 发送不管对方是否准备好，接收方收到也不确认，故是不可靠的
 * ✔ 可以广播发送
 * ✔ 发送数据结束时无需释放资源，开销小，速度快
 */
public class SocketIIII {
    /* 客户端 */
        @Test
        public void send () throws IOException {
            DatagramSocket socket = new DatagramSocket();
            byte[] data = "hello world".getBytes();
            DatagramPacket packet = new DatagramPacket( data, 0, data.length, InetAddress.getByName("127.0.0.1"), 8080 );
            socket.send( packet );
            socket.close();
        }

        @Test // 接收端
        public void receiver () throws IOException {
            DatagramSocket socket = new DatagramSocket( 8080 );
            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket( buffer, 0, buffer.length );
            socket.receive( packet );
            System.out.println( new String( packet.getData(), 0, packet.getLength() ) );
            socket.close();
        }
}
