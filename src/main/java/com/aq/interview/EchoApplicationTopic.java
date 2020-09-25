package com.aq.interview;

import jdk.nashorn.internal.ir.CatchNode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName EchoApplicationTopic
 * @Description : 实现要求：
 *      1、根据代码片段实现一个简单的SOCKET ECHO程序；
 *      2、接受到客户端连接后，服务端返回一个欢迎消息;
 *      3、接受到"bye"消息后， 服务端返回一个结束消息，并结束当前连接;
 *      4、支持通过telnet连接本服务端，并且可正常运行；
 *      5、注意代码注释书写。
 * @Author YaoAqiang
 * @Date 2020/6/17 16:43
 * @Version 1.0
 **/
public class EchoApplicationTopic {

    public static void main(String[] args) throws IOException, InterruptedException {

        // 启动服务端
        EchoServer server = new EchoServer(12345);
        server.startService();

        // 启动客户端
        EchoClient client = new EchoClient("localhost",12345);
        client.startClient();

        // 在控制台输入，服务端直接原文返回输入信息
        // 控制台结果示例：
        /**
         2020-03-31 16:58:44.049 - Welcome to My Echo Server.(from SERVER)

         Enter: hello!
         2020-03-31 16:58:55.452 - hello!(from SERVER)

         Enter: this is koal.
         2020-03-31 16:59:06.565 - this is koal.(from SERVER)

         Enter: what can i do for you?
         2020-03-31 16:59:12.828 - what can i do for you?(from SERVER)

         Enter: bye!
         2020-03-31 16:59:16.502 - Bye bye!(from SERVER)
         */
    }

}

class EchoServer {
    // TODO
    //创建一个serverSocket
    private final ServerSocket serverSocket;
    //创建一个构造器 传入端口
    public EchoServer(int port) throws IOException{
        //创建服务端    这里相当于创建了一个服务器并开启了通信端口port
        serverSocket = new ServerSocket(port);
    }

    //开始等待并接受客户端连接
    public void startService() throws IOException{
        //等待客户端的信息   如果客户端长时间没有与它建立连接  将会抛出异常 异常是在jdk中封装了的
        Socket client = serverSocket.accept();
        //进行socket通信处理
        handleClient(client);
    }
    //进行socket通信
    private void handleClient(Socket socket) throws IOException{

        //获取输入流
        InputStream in = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        //输出流
        OutputStream out = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(out, true);

        //设置缓冲区  1024个字节
        /*byte[] buffer = new byte[1024];

        //输出流的长度
        int n;
        //不断读入来自客户端的数据流  然后写回给客户端
        while((n=in.read(buffer))>0){
            out.write(buffer,0,n);
        }*/

        printWriter.println("2020-03-31 16:58:44.049 - Welcome to My Echo Server.(from SERVER)");
        out.flush();

        while (true) {
            String str = bufferedReader.readLine();
            String time = " 2020-03-31 16:58:55.452 - ";
            String serve = " (from SERVER)";
            if(str.equals("bye")) {
                printWriter.println(time + "Bye bye!" + serve);
                out.flush();
                break;
            }else {
                printWriter.println(time + str + serve);
                out.flush();
            }
        }

    }
    public static void main(String[] args) {
        try{
            // 启动服务端
            EchoServer server = new EchoServer(12345);
            server.startService();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class EchoClient {
    // TODO
    //创建socket
    private final Socket socket;

    //创建一个构造器 传入端口
    public EchoClient(String localhost, int port) throws IOException{
        socket = new Socket(localhost,port);
    }

    //和服务端进行通信
    public void startClient() throws IOException{
        //创建一个线程和服务端进行通信
        Thread readThread = new Thread(this::readResonse);
        //使用了lambda表达式相当于
        /*Thread readThread = new Thread(new Runnable(){
            @Override
            public void run(){
                readResonse();
            }
        });*/
        //开启线程
        readThread.start();
        //获取输入流
//        InputStream in = socket.getInputStream();
        //设置输出流
        OutputStream out = socket.getOutputStream();
        //创建缓冲区
        byte[] buffer = new byte[1024];
        //设置流的长度
        int n;
        //不断读取来自于服务端的流信息  并不断显示在客户端屏幕上
        while((n=System.in.read(buffer))>0){
            out.write(buffer,0,n);
        }
    }

    public void readResonse(){
        try{
            //创建输入流对象
            InputStream in = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //设置流的长度
            int n;

            PrintWriter printWriter = new PrintWriter(System.out);
            while (bufferedReader.read()>0) {
                String str = bufferedReader.readLine();
                printWriter.println(str);
                printWriter.flush();

                if(str.indexOf("bye")==-1) {
                    System.out.print("Enter: ");
                }
            }

            /*//读取流 并不断向服务端写入数据
            while((n=in.read(buffer))>0){
                System.out.write(buffer,0,n);

                System.out.print("Enter: ");
            }*/

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            // 启动客户端
            EchoClient client = new EchoClient("localhost",12345);
            client.startClient();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
