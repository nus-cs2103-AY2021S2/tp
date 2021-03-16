package seedu.address.commons.util;

import java.security.MessageDigest;

/**
 * A Utility class for returning  the MD5 hash of some input string.
 * This class is used  by {@link ImageRequestUtil}
 */

public class MD5Util {
    /**
     * Encodes a byte[] array into hexadecimal string.
     *
     * @param array of bytes
     * @return the hexadecimal string
     */
    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * Hashes a string using MD5 algorithm and returns it in hexadecimal format.
     *
     * @param message the input string to be hashed.
     * @return hexed md5-hash of input string
     * @throws Exception if messageDigest library fails to hash.
     */

    public static String md5Hex(String message) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return hex(md.digest(message.getBytes("CP1252")));
    }
}
//code from http://en.gravatar.com/site/implement/images/java/
