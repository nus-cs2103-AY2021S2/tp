package seedu.address.commons.util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MD5UtilTest {
    public static final String TEST_STRING = "MY_WAY_OR_THE HIGHWAY>>>>> ?4";
    public static final String HEX_OF_TEST_STRING = "4d595f5741595f4f525f54484520484947485741593e3e3e3e3e203f34";
    public static final String HEX_MD5_OF_TEST_STRING = "876f2076235e1a13906f886abbfa7597";

    @Test
    public void convertStringToHex_givenString_returnsHex() {
        assertEquals(HEX_OF_TEST_STRING, MD5Util.hex(TEST_STRING.getBytes()));
    }

    @Test
    public void convertStringToMD5Hex_givenString_success() throws Exception {
        assertEquals(HEX_MD5_OF_TEST_STRING, MD5Util.md5Hex(TEST_STRING));
    }
    //Exception not tested because it depends on external library.

}
