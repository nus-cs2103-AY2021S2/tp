package seedu.address.commons.util;

// import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class RegexUtilTest {

    // ---------------- Tests for REGEX_JAP_WORD --------------------------------------
    /*
     * Valid Jap words: only contains hiragana, katakana, and kanji
     */
    @Test
    public void regexJapWordValid() {
        String regex = RegexUtil.REGEX_JAP_WORD;

        // normal hiragana
        assertTrue("あ".matches(regex));
        assertTrue("が".matches(regex));
        assertTrue("っ".matches(regex));
        assertTrue("ぴ".matches(regex));
        assertTrue("きゅ".matches(regex));

        // normal katakana
        assertTrue("ア".matches(regex));
        assertTrue("ザ".matches(regex));
        assertTrue("ア".matches(regex));
        assertTrue("ッ".matches(regex));
        assertTrue("キュ".matches(regex));

        // normal kanji
        assertTrue("留学生".matches(regex));
        assertTrue("私".matches(regex));
        assertTrue("金魚".matches(regex));
        assertTrue("天気".matches(regex));

        // simple combinations
        assertTrue("散りぬるを".matches(regex));
        assertTrue("ツネナラムウヰノオクヤマ".matches(regex));
        assertTrue("浅き夢見じ酔ひもせず".matches(regex));
    }

    /*
     * Invalid Jap words: contains space, punctuation， latin and numeric characters
     */
    @Test
    public void regexJapWordInvalid() {
        String regex = RegexUtil.REGEX_JAP_WORD;

        assertFalse("".matches(regex));
        assertFalse(" ".matches(regex));
        assertFalse(" 天気".matches(regex));
        assertFalse("天気 ".matches(regex));
        assertFalse("天気。".matches(regex));
        assertFalse("天気、".matches(regex));
        assertFalse("、天気".matches(regex));
        assertFalse("天気 ".matches(regex));
        assertFalse(" 天気".matches(regex));
        assertFalse("123天気".matches(regex));
        assertFalse("latin天気".matches(regex));
        assertFalse("天気latin".matches(regex));
        assertFalse("&*()(&**天気".matches(regex));
        assertFalse("天気^&(^&&(^ ".matches(regex));
        assertFalse("weather".matches(regex));
        assertFalse("天123気weather".matches(regex));
        assertFalse("天気Ä".matches(regex));
    }
}