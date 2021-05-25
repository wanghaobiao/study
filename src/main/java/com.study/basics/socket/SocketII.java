package com.study.basics.socket;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @description: TCP编程，客户端发送文件给服务端，服务端将文件保存在本地。
 * @date: 2020/11/28 20:08
 */
public class SocketII {
    /* 客户端 */
    @Test
    public void fileClient() {
        Socket socket = null;
        OutputStream writer = null;
        BufferedInputStream bis = null;
        try {
            socket = new Socket( InetAddress.getByName("127.0.0.1"),8089);
            writer = socket.getOutputStream();
            //bis = new BufferedInputStream(new FileInputStream("E:\\SpringCloud\\study\\src\\main\\java\\com.study\\basics\\socket\\test.txt"));
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1){
                writer.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                    System.out.println("发送成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /* 服务端 */
    @Test
    public void fileServer() throws IOException { // 这里异常应该使用try-catch-finally
        ServerSocket socket = new ServerSocket(8089);
        System.out.println("正在等待客户端连接...");
        Socket clientSocket = socket.accept();

        System.out.println("客户端已连接IP地址为："+clientSocket.getInetAddress().getHostName());
        InputStream is = clientSocket.getInputStream();
        BufferedOutputStream reader = new BufferedOutputStream(new FileOutputStream("E:\\SpringCloud\\study\\src\\main\\java\\com.study\\basics\\socket\\test1.txt"));

        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            reader.write(buffer,0,len);
        }
        System.out.println("接收成功");

        socket.close();
        clientSocket.close();
        is.close();
        reader.close();
    }
}
