package com.fiveelements.unpay.xp.utils;

import static java.lang.Byte.MAX_VALUE;


/* loaded from: classes2.dex */
public class RC4 {
    public static final String ACCESSKEY = "ab4bb9da";
    public static final String charsetName = "UTF-8";

    public static String encrypt(String data) throws Exception {
        if (data == null) {
            return null;
        }
        return Base64.encode(encrypt_byte(data, ACCESSKEY));
    }

    public static String decrypt(String data) throws Exception {
        if (data == null) {
            return null;
        }
        return new String(RC4Base(Base64.decode(data), ACCESSKEY));
    }

    public static byte[] encrypt_byte(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        byte[] b_data = data.getBytes();
        return RC4Base(b_data, key);
    }

    private static byte[] initKey(String aKey) {
        byte[] b_key = aKey.getBytes();
        byte[] state = new byte[256];
        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int index1 = 0;
        int index2 = 0;
        if (b_key == null || b_key.length == 0) {
            return null;
        }
        for (int i2 = 0; i2 < 256; i2++) {
            index2 = ((b_key[index1] & MAX_VALUE) + (state[i2] & MAX_VALUE) + index2) & 255;
            byte tmp = state[i2];
            state[i2] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % b_key.length;
        }
        return state;
    }

    public static byte[] RC4Base(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = initKey(mKkey);
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 255;
            y = ((key[x] & MAX_VALUE) + y) & 255;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            int xorIndex = ((key[x] & MAX_VALUE) + (key[y] & MAX_VALUE)) & 255;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }
}