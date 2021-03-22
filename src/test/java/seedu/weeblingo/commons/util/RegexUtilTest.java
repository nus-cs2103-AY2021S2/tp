package seedu.weeblingo.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

    // ---------------- Tests for REGEX_JAP_SENTENCE --------------------------------------
    /*
     * Valid Jap sentences: only contains hiragana, katakana, kanji, english letters, numbers punctuations and symbols.
     */
    @Test
    public void regexJapSentenceValid() {
        String regex = RegexUtil.REGEX_JAP_SENTENCE;

        assertTrue("猫になりたい。".matches(regex));
        assertTrue("私は ただ、勉強したくないだけです。".matches(regex));
        assertTrue("CS2103Tははははは".matches(regex));
        assertTrue("収入は1000.25円。".matches(regex));
        assertTrue("12345あいうえお".matches(regex));
        assertTrue("ジンボはリンゴを食べる。".matches(regex));
        assertTrue("。。。".matches(regex));
    }

    // ---------------- Tests for REGEX_JAP_SENTENCE --------------------------------------
    /*
     * Invalid Jap sentences: empty sentences, contain non-english & non-japanese & non-chinese characters
     */
    @Test
    public void regexJapSentenceInvalid() {
        String regex = RegexUtil.REGEX_JAP_SENTENCE;

        assertFalse("".matches(regex));
        assertFalse("  ".matches(regex));
        assertFalse(" 収入は1000.25円。".matches(regex));
        assertFalse("Glück".matches(regex));
        assertFalse("Adiós".matches(regex));
        assertFalse("아니요".matches(regex));
    }

    // ---------------- Tests for REGEX_ENG_WORD --------------------------------------
    /*
     * Both valid and invalid cases tests are here.
     */
    @Test
    public void regexEngWord() {
        String regex = RegexUtil.REGEX_ENG_WORD;

        assertTrue("a".matches(regex));
        assertTrue("A".matches(regex));
        assertTrue("Yes".matches(regex));

        assertFalse("".matches(regex));
        assertFalse(" ".matches(regex));
        assertFalse("No.".matches(regex));
        assertFalse("1word".matches(regex));
        assertFalse("two words".matches(regex));
        assertFalse("Adiós".matches(regex));
        assertFalse("아니요".matches(regex));
    }

    // ---------------- Tests for REGEX_ENG_SENTENCE --------------------------------------
    /*
     * Both valid and invalid cases tests are here.
     */
    @Test
    public void regexEngSentence() {
        String regex = RegexUtil.REGEX_ENG_SENTENCE;

        assertTrue("a".matches(regex));
        assertTrue("A".matches(regex));
        assertTrue("Yes".matches(regex));
        assertTrue("True.".matches(regex));
        assertTrue("Haha means happiness.".matches(regex));
        assertTrue("1 means one.".matches(regex));
        assertTrue("......".matches(regex));
        assertTrue("I ate dinner.".matches(regex));
        assertTrue("We all agreed; it was a magnificent evening.".matches(regex));
        assertTrue("Oh, how I'd love to go!".matches(regex));

        assertFalse("".matches(regex));
        assertFalse(" ".matches(regex));
        assertFalse("这不对！".matches(regex));
        assertFalse("Adiós".matches(regex));
        assertFalse("아니요".matches(regex));
    }
}
