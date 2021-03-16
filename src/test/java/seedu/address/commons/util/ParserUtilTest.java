package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;

public class ParserUtilTest {

    @Test
    public void null_extractKeywordsFromEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.extractKeywordsFromEmail(null));
    }

    @Test
    public void extractKeywordsFromEmail() {
        // Empty Strings
        assertEquals(ParserUtil.extractKeywordsFromEmail(""), "");
        assertEquals(ParserUtil.extractKeywordsFromEmail("   "), "");

        // Normal usage
        assertEquals(ParserUtil.extractKeywordsFromEmail("a@a.com"), "a a com");
        assertEquals(ParserUtil.extractKeywordsFromEmail("deach@gmail.com"), "deach gmail com");

        // None email format
        assertEquals(ParserUtil.extractKeywordsFromEmail("deachgmailcom"), "deachgmailcom");

        // Partial email format
        assertEquals(ParserUtil.extractKeywordsFromEmail("don@hotmailcom"), "don hotmailcom");
        assertEquals(ParserUtil.extractKeywordsFromEmail("deanhotmail.com"), "deanhotmail com");

        // Multiple email symbols in between (for general usage)
        assertEquals(ParserUtil.extractKeywordsFromEmail("cas@@gmail.com"), "cas gmail com");
    }

}
