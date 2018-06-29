package com.horen.horenbase.utils;

import android.os.Build.VERSION;
import android.text.Html;
import android.util.Base64;

import com.bumptech.glide.load.Key;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeUtils {
    private EncodeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String urlEncode(String input) {
        return urlEncode(input, Key.STRING_CHARSET_NAME);
    }

    public static String urlEncode(String input, String charset) {
        try {
            input = URLEncoder.encode(input, charset);
        } catch (UnsupportedEncodingException e) {
        }
        return input;
    }

    public static String urlDecode(String input) {
        return urlDecode(input, Key.STRING_CHARSET_NAME);
    }

    public static String urlDecode(String input, String charset) {
        try {
            input = URLDecoder.decode(input, charset);
        } catch (UnsupportedEncodingException e) {
        }
        return input;
    }

    public static byte[] base64Encode(String input) {
        return base64Encode(input.getBytes());
    }

    public static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, 2);
    }

    public static String base64Encode2String(byte[] input) {
        return Base64.encodeToString(input, 2);
    }

    public static byte[] base64Decode(String input) {
        return Base64.decode(input, 2);
    }

    public static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, 2);
    }

    public static byte[] base64UrlSafeEncode(String input) {
        return Base64.encode(input.getBytes(), 8);
    }

    public static String htmlEncode(String input) {
        if (VERSION.SDK_INT >= 16) {
            return Html.escapeHtml(input);
        }
        StringBuilder out = new StringBuilder();
        int i = 0;
        int len = input.length();
        while (i < len) {
            char c = input.charAt(i);
            if (c == '<') {
                out.append("&lt;");
            } else if (c == '>') {
                out.append("&gt;");
            } else if (c == '&') {
                out.append("&amp;");
            } else if (c < '?' || c > '?') {
                if (c > '~' || c < ' ') {
                    out.append("&#").append(c).append(";");
                } else if (c == ' ') {
                    while (i + 1 < len && input.charAt(i + 1) == ' ') {
                        out.append("&nbsp;");
                        i++;
                    }
                    out.append(' ');
                } else {
                    out.append(c);
                }
            } else if (c < '?' && i + 1 < len) {
                char d = input.charAt(i + 1);
                if (d >= '?' && d <= '?') {
                    i++;
                    out.append("&#").append((65536 | ((c - 55296) << 10)) | (d - 56320)).append(";");
                }
            }
            i++;
        }
        return out.toString();
    }

    public static String htmlDecode(String input) {
        return Html.fromHtml(input).toString();
    }
}
