package com.horen.horenbase.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.horen.base.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class ConvertUtils {
    static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String bytes2HexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        if (len <= 0) {
            return null;
        }
        char[] ret = new char[(len << 1)];
        int j = 0;
        for (int i = 0; i < len; i++) {
            int i2 = j + 1;
            ret[j] = hexDigits[(bytes[i] >>> 4) & 15];
            j = i2 + 1;
            ret[i2] = hexDigits[bytes[i] & 15];
        }
        return new String(ret);
    }

    public static byte[] hexString2Bytes(String hexString) {
        if (StringUtils.isSpace(hexString)) {
            return null;
        }
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len++;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[(len >> 1)];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) ((hex2Dec(hexBytes[i]) << 4) | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }

    private static int hex2Dec(char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - 48;
        }
        if (hexChar >= 'A' && hexChar <= 'F') {
            return (hexChar - 65) + 10;
        }
        throw new IllegalArgumentException();
    }

    public static byte[] chars2Bytes(char[] chars) {
        if (chars == null || chars.length <= 0) {
            return null;
        }
        int len = chars.length;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) chars[i];
        }
        return bytes;
    }

    public static char[] bytes2Chars(byte[] bytes) {
        char[] cArr = null;
        if (bytes != null) {
            int len = bytes.length;
            if (len > 0) {
                cArr = new char[len];
                for (int i = 0; i < len; i++) {
                    cArr[i] = (char) (bytes[i] & 255);
                }
            }
        }
        return cArr;
    }

    public static double byte2Size(long byteNum, ConstUtils.MemoryUnit unit) {
        if (byteNum < 0) {
            return -1.0d;
        }
        switch (unit) {
            case KB:
                return ((double) byteNum) / 1024.0d;
            case MB:
                return ((double) byteNum) / 1048576.0d;
            case GB:
                return ((double) byteNum) / 1.073741824E9d;
            default:
                return ((double) byteNum) / 1.0d;
        }
    }

    public static long size2Byte(long size, ConstUtils.MemoryUnit unit) {
        if (size < 0) {
            return -1;
        }
        switch (unit) {
            case KB:
                return 1024 * size;
            case MB:
                return 1048576 * size;
            case GB:
                return IjkMediaMeta.AV_CH_STEREO_RIGHT * size;
            default:
                return 1 * size;
        }
    }

    public static String byte2FitSize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        }
        if (byteNum < 1024) {
            return String.format(Locale.getDefault(), "%.2fB", new Object[]{Double.valueOf((double) byteNum)});
        } else if (byteNum < 1048576) {
            return String.format(Locale.getDefault(), "%.2fKB", new Object[]{Double.valueOf(((double) byteNum) / 1024.0d)});
        } else if (byteNum < IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            return String.format(Locale.getDefault(), "%.2fMB", new Object[]{Double.valueOf(((double) byteNum) / 1048576.0d)});
        } else {
            return String.format(Locale.getDefault(), "%.2fGB", new Object[]{Double.valueOf(((double) byteNum) / 1.073741824E9d)});
        }
    }

    public static String bytes2Bits(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            for (int j = 7; j >= 0; j--) {
                sb.append(((aByte >> j) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    public static byte[] bits2Bytes(String bits) {
        int i;
        int lenMod = bits.length() % 8;
        int byteLen = bits.length() / 8;
        if (lenMod != 0) {
            for (i = lenMod; i < 8; i++) {
                bits = "0" + bits;
            }
            byteLen++;
        }
        byte[] bytes = new byte[byteLen];
        for (i = 0; i < byteLen; i++) {
            for (int j = 0; j < 8; j++) {
                bytes[i] = (byte) (bytes[i] << 1);
                bytes[i] = (byte) (bytes[i] | (bits.charAt((i * 8) + j) - 48));
            }
        }
        return bytes;
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream is) {
        if (is == null) {
            return null;
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            while (true) {
                int len = is.read(b, 0, 1024);
                if (len != -1) {
                    os.write(b, 0, len);
                } else {
                    CloseUtils.closeIO(is);
                    return os;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            CloseUtils.closeIO(is);
            return null;
        } catch (Throwable th) {
            CloseUtils.closeIO(is);
        }
        return null;
    }

    public ByteArrayInputStream output2InputStream(OutputStream out) {
        if (out == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
    }

    public static byte[] inputStream2Bytes(InputStream is) {
        if (is == null) {
            return null;
        }
        return input2OutputStream(is).toByteArray();
    }

    public static InputStream bytes2InputStream(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bytes);
    }

    public static byte[] outputStream2Bytes(OutputStream out) {
        if (out == null) {
            return null;
        }
        return ((ByteArrayOutputStream) out).toByteArray();
    }

    public static OutputStream bytes2OutputStream(byte[] bytes) throws Throwable {
        IOException e;
        OutputStream os;
        Throwable th;
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        ByteArrayOutputStream os2 = null;
        try {
            OutputStream os3 = new ByteArrayOutputStream();
            try {
                os3.write(bytes);
                CloseUtils.closeIO(os3);
                return os3;
            } catch (IOException e2) {
                e = e2;
                os = os3;
                try {
                    e.printStackTrace();
                    CloseUtils.closeIO(os2);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    CloseUtils.closeIO(os2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                os = os3;
                CloseUtils.closeIO(os2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            CloseUtils.closeIO(os2);
            return null;
        }
    }

    public static String inputStream2String(InputStream is, String charsetName) {
        if (is == null || StringUtils.isSpace(charsetName)) {
            return null;
        }
        try {
            return new String(inputStream2Bytes(is), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream string2InputStream(String string, String charsetName) {
        if (string == null || StringUtils.isSpace(charsetName)) {
            return null;
        }
        try {
            return new ByteArrayInputStream(string.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String outputStream2String(OutputStream out, String charsetName) {
        if (out == null || StringUtils.isSpace(charsetName)) {
            return null;
        }
        try {
            return new String(outputStream2Bytes(out), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static OutputStream string2OutputStream(String string, String charsetName) {
        OutputStream outputStream = null;
        if (!(string == null || StringUtils.isSpace(charsetName))) {
            try {
                outputStream = bytes2OutputStream(string.getBytes(charsetName));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return outputStream;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap bytes2Bitmap(byte[] bytes) {
        return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(res, bitmap);
    }

    public static byte[] drawable2Bytes(Drawable drawable, CompressFormat format) {
        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);
    }

    public static Drawable bytes2Drawable(Resources res, byte[] bytes) {
        return res == null ? null : bitmap2Drawable(res, bytes2Bitmap(bytes));
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap ret = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return ret;
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        return (int) ((spValue * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        return (int) ((pxValue / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
