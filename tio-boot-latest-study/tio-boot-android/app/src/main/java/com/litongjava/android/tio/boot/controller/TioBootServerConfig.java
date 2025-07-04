package com.litongjava.android.tio.boot.controller;

import com.litongjava.context.BootConfiguration;
import com.litongjava.tio.boot.http.handler.controller.TioBootHttpControllerRouter;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

import java.util.ArrayList;
import java.util.List;

public class TioBootServerConfig implements BootConfiguration {
    @Override
    public void config() throws Exception {
        TioBootHttpControllerRouter controllerRouter = TioBootServer.me().getControllerRouter();
        if (controllerRouter != null) {
            List<Class<?>> scannedClasses = new ArrayList<>();
            scannedClasses.add(IndexController.class);
            controllerRouter.addControllers(scannedClasses);
        }

        HttpRequestRouter requestRouter = TioBootServer.me().getRequestRouter();
        if (requestRouter != null) {
            HelloHandler helloHandler = new HelloHandler();
            requestRouter.add("/hi", helloHandler::hi);
            requestRouter.add("/hello", helloHandler::hello);
        }

    }
}
