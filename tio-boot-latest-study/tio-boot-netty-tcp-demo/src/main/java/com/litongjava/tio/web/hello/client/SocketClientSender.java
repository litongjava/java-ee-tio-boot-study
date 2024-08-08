package com.litongjava.tio.web.hello.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientSender {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("127.0.0.1", 17902);
      OutputStream outputStream = socket.getOutputStream();
      PrintWriter printWriter = new PrintWriter(outputStream);
      printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
      System.out.println("send message");
      printWriter.flush();
      socket.shutdownOutput();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
