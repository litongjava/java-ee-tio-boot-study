package com.litongjava.android.tio.boot;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.litongjava.android.tio.boot.controller.TioBootServerApp;
import com.litongjava.android.utils.acp.AcpUtils;
import com.litongjava.android.utils.toast.ToastUtils;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.mylhyl.acp.AcpListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);
    ViewInjectUtils.injectActivity(this, this);
  }

  @OnClick(R.id.startBtn)
  public void startBtnOnClick(View view) {
    String[] permissions = {
      //写入外部设备权限
      Manifest.permission.ACCESS_NETWORK_STATE,
      Manifest.permission.ACCESS_WIFI_STATE,
      Manifest.permission.INTERNET,
      Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE

    };
    //创建acpListener
    AcpListener acpListener = new AcpListener() {
      @Override
      public void onGranted() {
        TioBootServerApp.run();
      }

      @Override
      public void onDenied(List<String> permissions) {
        ToastUtils.defaultToast(getApplicationContext(), permissions.toString() + "权限拒绝,无法写入日志");
      }
    };

    AcpUtils.requestPermissions(this, permissions, acpListener);

  }
}