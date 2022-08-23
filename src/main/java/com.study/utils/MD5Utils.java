package com.study.utils;

import com.server.basis.util.DateUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class MD5Utils {

    public static String md532(String source)  {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        byte[] resultBytes = messageDigest.digest(sourceBytes);

        StringBuilder builder = new StringBuilder();
        for (byte b : resultBytes) {
            int val = b & 0xff;
            if (val < 16) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(val));
        }
        return builder.toString();
    }

    public static String md532(byte[] source) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] resultBytes = messageDigest.digest(source);

        StringBuilder builder = new StringBuilder();
        for (byte b : resultBytes) {
            int val = b & 0xff;
            if (val < 16) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(val));
        }
        return builder.toString();
    }

    public static String md516(String source) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        byte[] resultBytes = messageDigest.digest(sourceBytes);

        StringBuilder builder = new StringBuilder();
        for (byte b : resultBytes) {
            builder.append(Integer.toHexString(b & 0x0f));
        }

        return builder.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String key = md532( "0F022404AAE7411B9EC0FD98671671CE"+ DateUtil.toString( new Date(),DateUtil.DATE_SHORTS));
        System.out.println(key);
        /*String RE = HttpUtil.get( "http://gaj.longnan.gov.cn/weixin/GetAccessToken.html?key=" +key , new HashMap<>());
        System.out.println( JSONObject.parseObject( RE ).get( "access_token" ) );*/
    }

}
