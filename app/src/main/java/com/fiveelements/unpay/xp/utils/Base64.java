package com.fiveelements.unpay.xp.utils;

import com.alibaba.fastjson.asm.Opcodes;

import org.apache.commons.codec.CharEncoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/* loaded from: classes2.dex */
public class Base64 {
    private static char[] base64EncodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 0x3F, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String encode(byte[] data) throws Exception {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int b1 = 0;
        while (true) {
            if (b1 >= len) {
                break;
            }
            int i = b1 + 1;
            int b12 = data[b1] & 255;
            if (i == len) {
                sb.append(base64EncodeChars[b12 >>> 2]);
                sb.append(base64EncodeChars[(b12 & 3) << 4]);
                sb.append("==");
                break;
            }
            int i2 = i + 1;
            int b2 = data[i] & 255;
            if (i2 == len) {
                sb.append(base64EncodeChars[b12 >>> 2]);
                sb.append(base64EncodeChars[((b12 & 3) << 4) | ((b2 & 240) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 15) << 2]);
                sb.append("=");
                break;
            }
            int i3 = i2 + 1;
            int b3 = data[i2] & 255;
            sb.append(base64EncodeChars[b12 >>> 2]);
            sb.append(base64EncodeChars[((b12 & 3) << 4) | ((b2 & 240) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 15) << 2) | ((b3 & Opcodes.CHECKCAST) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 63]);
            b1 = i3;
        }
        return sb.toString();
    }

    public static byte[] decode(String str) throws Exception {
        int b2;
        int b1;
        int b3;
        int b22;
        int b4;
        int b32;
        int i;
        int b42;
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes(CharEncoding.US_ASCII);
        int len = data.length;
        int b12 = 0;
        while (b12 < len) {
            while (true) {
                byte[] bArr = base64DecodeChars;
                b2 = b12 + 1;
                int i2 = data[b12];
                b1 = bArr[i2];
                if (b2 >= len || b1 != -1) {
                    break;
                }
                b12 = b2;
            }
            if (b1 == -1) {
                break;
            }
            while (true) {
                byte[] bArr2 = base64DecodeChars;
                b3 = b2 + 1;
                int i3 = data[b2];
                b22 = bArr2[i3];
                if (b3 >= len || b22 != -1) {
                    break;
                }
                b2 = b3;
            }
            if (b22 == -1) {
                break;
            }
            sb.append((char) ((b1 << 2) | ((b22 & 48) >>> 4)));
            while (true) {
                b4 = b3 + 1;
                int b33 = data[b3];
                if (b33 == 61) {
                    return sb.toString().getBytes("iso8859-1");
                }
                b32 = base64DecodeChars[b33];
                if (b4 >= len || b32 != -1) {
                    break;
                }
                b3 = b4;
            }
            if (b32 == -1) {
                break;
            }
            sb.append((char) (((b22 & 15) << 4) | ((b32 & 60) >>> 2)));
            while (true) {
                i = b4 + 1;
                int b43 = data[b4];
                if (b43 == 61) {
                    return sb.toString().getBytes("iso8859-1");
                }
                b42 = base64DecodeChars[b43];
                if (i >= len || b42 != -1) {
                    break;
                }
                b4 = i;
            }
            if (b42 == -1) {
                break;
            }
            sb.append((char) (((b32 & 3) << 6) | b42));
            b12 = i;
        }
        return sb.toString().getBytes("iso8859-1");
    }

    public String getEncryptedPhone(String sec, String phone) throws Exception {
        byte[] arrayOfByte = {100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32};
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(decode(sec), "AES");
        IvParameterSpec localIvParameterSpec = new IvParameterSpec(arrayOfByte);
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(1, localSecretKeySpec, localIvParameterSpec);
        String str = encode(localCipher.doFinal(phone.getBytes("UTF-8")));
        return str;
    }
}