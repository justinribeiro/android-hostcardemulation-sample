package com.justinribeiro.demo.apps.hostcardemulation;

/**
 * Created by justin.ribeiro on 10/28/2014.
 */

/**
 * Just a tiny class to dump things that I may want to use
 *
 * AKA: you probably don't need this, but you might :-)
 */
public class utils {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Simple way to output byte[] to hex (my readable preference)
     * This version quite speedy; originally from: http://stackoverflow.com/a/9855338
     *
     * @param bytes yourByteArray
     * @return string
     *
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Constant-time Byte Array Comparison
     * Less overheard, safer. Originally from: http://codahale.com/a-lesson-in-timing-attacks/
     *
     * @param bytes yourByteArrayA
     * @param bytes yourByteArrayB
     * @return boolean
     *
     */
    public static boolean isEqual(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }
}
