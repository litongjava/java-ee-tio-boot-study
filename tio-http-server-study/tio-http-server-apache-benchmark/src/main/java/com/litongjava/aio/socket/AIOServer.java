package com.litongjava.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOServer {
  public static void main(String[] args) throws Exception {
    AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(8080));

    run(serverSocketChannel);

    // keep the server running
    Thread.currentThread().join();
  }

  private static void run(AsynchronousServerSocketChannel serverSocketChannel) {
    serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
      @Override
      public void completed(AsynchronousSocketChannel channel, Void attachment) {
        // handle completed connection
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
          @Override
          public void completed(Integer result, ByteBuffer attachment) {
            // 处理读取到的数据
            if (result > 0) {
              // 准备HTTP响应数据
              String httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Length: 13\r\n" + "Content-Type: text/plain\r\n"
                  + "\r\n" + "Hello, world!";

              ByteBuffer writeBuffer = ByteBuffer.allocate(httpResponse.length());
              writeBuffer.put(httpResponse.getBytes());
              writeBuffer.flip();

              // 异步写入HTTP响应数据到客户端
              channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                  // 写操作完成后的处理
                  try {
                    channel.close();
                  } catch (IOException e) {
                    // 异常处理
                  }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer buffer) {
                  // 写操作失败的处理
                  try {
                    channel.close();
                  } catch (IOException e) {
                    // 异常处理
                  }
                }
              });
            }
          }

          @Override
          public void failed(Throwable exc, ByteBuffer attachment) {
            // 读操作失败的处理
            try {
              channel.close();
            } catch (IOException e) {
              // 异常处理
            }
          }
        });

        serverSocketChannel.accept(null, this); // 接受下一个连接
      }

      @Override
      public void failed(Throwable exc, Void attachment) {
        // 连接失败的处理
      }
    });
  }
}
