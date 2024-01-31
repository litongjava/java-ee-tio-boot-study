package demo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import com.litongjava.tio.websocket.client.WebSocket;
import com.litongjava.tio.websocket.client.WsClient;
import com.litongjava.tio.websocket.client.config.WsClientConfig;
import com.litongjava.tio.websocket.client.event.CloseEvent;
import com.litongjava.tio.websocket.client.event.ErrorEvent;
import com.litongjava.tio.websocket.client.event.MessageEvent;
import com.litongjava.tio.websocket.client.event.OpenEvent;
import com.litongjava.tio.websocket.common.WsPacket;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class TioWebSocketDemo {

  public static void main(String[] args) throws Exception {
    Map<Long, Boolean> sent = new ConcurrentHashMap<>();
    int total = 1000;
    String uri = "ws://localhost/hello";

    // onNext
    io.reactivex.functions.Consumer<? super List<Object>> onNext = x -> {
      Boolean all = sent.values().stream().reduce(true, (p, c) -> p && c);
      if (all) {
        System.out.println("All sent success! ");
      }
    };

    // complete
    Subject<Object> complete = PublishSubject.create().toSerialized();
    // subscribe
    complete.buffer(total).subscribe(onNext);

    // wsClientConfig
    Consumer<OpenEvent> onOpen = e -> System.out.println("opened");

    Consumer<MessageEvent> onMessage = e -> {
      WsPacket data = e.data;
      Long id = data.getId();
      String wsBodyText = data.getWsBodyText();
      sent.put(id, true);
      System.out.println("recv: " + wsBodyText);
      complete.onNext(id);
    };

    Consumer<CloseEvent> onClose = e -> System.out.printf("on close: %d, %s, %s\n", e.code, e.reason, e.wasClean);
    Consumer<ErrorEvent> onError = e -> System.out.println(String.format("on error: %s", e.msg));
    Consumer<Throwable> onThrows = Throwable::printStackTrace;

    // wsClientConfig
    WsClientConfig wsClientConfig = new WsClientConfig(onOpen, onMessage, onClose, onError, onThrows);

    // create
    WsClient echo = WsClient.create(uri, wsClientConfig);

    // connect
    WebSocket ws = echo.connect();

    // sent
    for (int i = 0; i < total; i++) {
      ws.send("" + i);
      System.out.println("sent: " + i);
    }
  }
}
