package com.horen.movie.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static final String DEFAULT_KEY = "1400101173000000";

    public static String decrypt(String content, String key) {
        String result = null;
        try {
            byte[] contentBytes = Base64.decode(content, Base64.NO_WRAP);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, skeySpec);
            byte[] decryptResult = cipher.doFinal(contentBytes);
            if (decryptResult != null) {
                result = new String(decryptResult, "UTF-8");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String content) {
        return decrypt(content, DEFAULT_KEY);
    }

    public static String encrypt(String content) {
        return encrypt(content, DEFAULT_KEY);
    }

    public static String encrypt(String content, String key) {
        byte[] encryptResult = null;
        try {
            byte[] contentBytes = content.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, skeySpec);
            encryptResult = cipher.doFinal(contentBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (encryptResult != null) {
            return Base64.encodeToString(encryptResult, 0);
        }
        return null;
    }
}
