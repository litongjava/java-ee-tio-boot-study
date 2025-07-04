package com.litongjava.android.tio.boot.controller;

import com.litongjava.context.BootConfiguration;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

public class TioBootServerConfig implements BootConfiguration {
    @Override
    public void config() throws Exception {
        HttpRequestRouter requestRouter = TioBootServer.me().getRequestRouter();
        if (requestRouter != null) {
            HelloHandler helloHandler = new HelloHandler();
            requestRouter.add("/hi", helloHandler::hi);
            requestRouter.add("/hello", helloHandler::hello);
        }

    }
}
