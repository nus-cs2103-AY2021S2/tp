package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_IDENTIFIER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventName;

public class ParserUtilTest {
    public static final Long INTEGER_MAX = Integer.toUnsignedLong(Integer.MAX_VALUE);
    public static final Long LARGE_NUMBER = INTEGER_MAX + 2;

    private static final String INVALID_NAME = "CS2030@";
    private static final String INVALID_STATUS = "ASD";
    private static final String INVALID_PRIORITY = "CZXA";
    private static final String INVALID_DESCRIPTION = "ASD@!S$Z";

    private static final String VALID_NAME = "CS2030";
    private static final String VALID_STATUS = "TODO";
    private static final String VALID_PRIORITY = "HIGH";
    private static final String VALID_DESCRIPTION = "Assignment";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIdentifier_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIdentifier("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_IDENTIFIER, ()
            -> ParserUtil.parseIdentifier(Long.toString(LARGE_NUMBER)));
    }

    @Test
    public void parseIdentifier_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(IDENTIFIER_FIRST_EVENT, ParserUtil.parseIdentifier("1"));

        // Leading and trailing whitespaces
        assertEquals(IDENTIFIER_FIRST_EVENT, ParserUtil.parseIdentifier("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        EventName expectedName = new EventName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseEventName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        EventName expectedName = new EventName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseEventName(nameWithWhitespace));
    }

}
