package com.example.lm_android_id;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceHelper {
    public String getDeviceId(Context context) {
        String androidId = getAndroidId(context);
        if (TextUtils.isEmpty(androidId) || androidId.matches("0+")) {
            return genUnique(context);
        }
        return androidId;
    }

    private String genUnique(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String utdId = Settings.System.getString(contentResolver, "mqBRboGZkQPcAkyk");
        if (!TextUtils.isEmpty(utdId)) {
            return md5(utdId);
        }
        String utdIdBase64 = Settings.System.getString(contentResolver, "dxCRMxhQkdGePGnp");
        if (!TextUtils.isEmpty(utdIdBase64)) {
            return md5(utdIdBase64);
        }

        StringBuilder simulationSerialBuilder = getStringBuilder();
        return md5(simulationSerialBuilder.toString());
    }

    private static StringBuilder getStringBuilder() {
        StringBuilder simulationSerialBuilder = new StringBuilder("35");
        simulationSerialBuilder.append(Build.BOARD.length() % 10)
                .append(Build.BRAND.length() % 10)
                .append(Build.CPU_ABI.length() % 10)
                .append(Build.DEVICE.length() % 10)
                .append(Build.DISPLAY.length() % 10)
                .append(Build.HOST.length() % 10)
                .append(Build.ID.length() % 10)
                .append(Build.MANUFACTURER.length() % 10)
                .append(Build.MODEL.length() % 10)
                .append(Build.PRODUCT.length() % 10)
                .append(Build.TAGS.length() % 10)
                .append(Build.TYPE.length() % 10)
                .append(Build.USER.length() % 10);
        return simulationSerialBuilder;
    }

    private String md5(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byte2hex(md.digest(content.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        int n = 0;
        while (n < b.length) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
            n++;
        }
        return hs.toString();
    }

    @SuppressLint("HardwareIds")
    private String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }
}
