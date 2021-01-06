package com.atguigu.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author davidPhon
 * @create 2021-01-06-15:39
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
//      1  创建一个线程池
//      2  创建线程与客户端通信
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        创建serverSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("启动");
        while(true){
//            监听，等待客户端连接
            final Socket socket = serverSocket.accept();
//            启动一个线程
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
//                    可以和客户端通信
                    handler(socket);
                }
            });





        }


    }
//       编写一个handler与客户端通讯
    public static void handler(Socket socket){
        try {
            byte[] bytes = new byte[1024];
//            通过socket获取输入流
            InputStream inputStream = socket.getInputStream();

//            循环读取客户端发送的数据
            while(true){
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("关闭连接");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
