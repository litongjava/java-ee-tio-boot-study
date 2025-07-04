package com.litongjava.android.tio.boot.controller;


import com.blankj.utilcode.util.NetworkUtils;
import com.litongjava.tio.boot.TioApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TioBootServerApp {
    private static Logger log = LoggerFactory.getLogger(TioBootServerApp.class);

    public static void run() {
        long start = System.currentTimeMillis();
        TioBootServerConfig tioBootServerConfig = new TioBootServerConfig();
        String[] args = new String[]{"--server.port=10051"};
        TioApplication.run(TioBootServerApp.class, tioBootServerConfig, args);
        String ipAddressByWifi = NetworkUtils.getIpAddressByWifi();
        log.info("ipAddressByWifi:{}", ipAddressByWifi);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "(ms)");
    }
}
