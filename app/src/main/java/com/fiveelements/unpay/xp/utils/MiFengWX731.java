package com.fiveelements.unpay.xp.utils;

import android.os.Build;

import java.util.Base64;

public class MiFengWX731 {

    public static byte[] EncodeKey(String aKey) {
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
            index2 = ((b_key[index1] & 255) + (state[i2] & 255) + index2) & 255;
            byte tmp = state[i2];
            state[i2] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % b_key.length;
        }
        return state;
    }

    public static byte[] EncodeOrDecode(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = EncodeKey(mKkey);
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 255;
            y = ((key[x] & 255) + y) & 255;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            result[i] = (byte) (input[i] ^ key[((key[x] & 255) + (key[y] & 255)) & 255]);
        }
        return result;
    }

    public static String Encode(String str, String key) {
        if (str == "") {
            return "";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(EncodeOrDecode(str.getBytes(), key));
        } else {
            return android.util.Base64.encodeToString(EncodeOrDecode(str.getBytes(), key), android.util.Base64.NO_WRAP);
        }
    }

    public static String Decode(String str, String key) {
        if (str == "") {
            return "";
        }
        byte[] input = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            input = Base64.getDecoder().decode(str.getBytes());
        } else {
            input = android.util.Base64.decode(str, android.util.Base64.NO_WRAP);
        }
        return new String(EncodeOrDecode(input, key));
    }

}
