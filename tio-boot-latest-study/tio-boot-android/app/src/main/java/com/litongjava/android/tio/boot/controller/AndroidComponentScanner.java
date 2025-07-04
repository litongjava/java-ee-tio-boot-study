package com.litongjava.android.tio.boot.controller;

import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.litongjava.jfinal.aop.process.ComponentAnnotation;
import com.litongjava.jfinal.aop.scanner.ComponentScanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Android 平台下的 ComponentScanner 实现，利用 DexFile 枚举 APK 中的类并根据注解筛选。
 */
public class AndroidComponentScanner implements ComponentScanner {
    private static final Logger log = LoggerFactory.getLogger(AndroidComponentScanner.class);

    @Override
    public List<Class<?>> scan(Class<?>[] primarySources, boolean printScannedClasses) throws Exception {
        // 获取全局 Application Context
        Context context = Utils.getApp();
        // APK 路径
        String apkPath = context.getPackageCodePath();
        // 打开 DexFile
        DexFile dexFile = new DexFile(apkPath);
        Enumeration<String> entries = dexFile.entries();
        List<Class<?>> classes = new ArrayList<>();
        // 需要扫描的注解列表
        List<Class<? extends Annotation>> annotations = ComponentAnnotation.getAnnotations();

        while (entries.hasMoreElements()) {
            String className = entries.nextElement();
            // 仅扫描应用包内的类
            if (!className.startsWith(context.getPackageName())) continue;
            Class<?> clazz;
            try {
                clazz = Class.forName(className, false, context.getClassLoader());
            } catch (Throwable t) {
                // 跳过无法加载的类
                continue;
            }
            // 检查注解
            for (Class<? extends Annotation> annotation : annotations) {
                if (clazz.isAnnotationPresent(annotation)) {
                    classes.add(clazz);
                    if (printScannedClasses) {
                        log.info("Scanned component: {}", className);
                    }
                    break;
                }
            }
        }
        return classes;
    }
}
