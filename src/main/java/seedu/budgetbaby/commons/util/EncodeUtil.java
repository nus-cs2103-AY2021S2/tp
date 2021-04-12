package seedu.budgetbaby.commons.util;

import java.util.Base64;

public class EncodeUtil {

    // Changing ENCODING_COUNT requires updating of test data
    private static final int ENCODING_COUNT = 2;

    public static String getEncodedString(String decodedString) {
        String encodedString = decodedString;
        for (int i = 0; i < ENCODING_COUNT; i++) {
            encodedString = Base64.getEncoder().encodeToString(encodedString.getBytes());
        }
        return encodedString;
    }

    public static String getDecodedString(String encodedString) {
        String decodedString = encodedString;
        for (int i = 0; i < ENCODING_COUNT; i++) {
            decodedString = new String(Base64.getDecoder().decode(decodedString.getBytes()));
        }
        return decodedString;
    }
}
