package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FindCommandParserUtilTest {

    @Test
    public void checkEmptyInputField_invalidInput_throwsParseException() {
        String input = "";

        assertThrows(ParseException.class, ()
            -> FindCommandParserUtil.checkEmptyInputField(input, "input"));
        assertThrows(ParseException.class, ()
            -> FindCommandParserUtil.checkEmptyInputField(input, "t/"));
        assertThrows(ParseException.class, ()
            -> FindCommandParserUtil.checkEmptyInputField(input, "d/"));
    }

    @Test
    public void handleSearchByTag_invalidInput_throwsParseException() {
        String[] keywords = new String[]{"t/"};

        assertThrows(ParseException.class, ()
            -> FindCommandParserUtil.handleSearchByTag(keywords));
    }

    @Test
    public void handleSearchByDescription_invalidInput_throwsParseException() {
        String[] keywords = new String[]{"d/"};

        assertThrows(ParseException.class, ()
            -> FindCommandParserUtil.handleSearchByDescription(keywords));
    }

    @Test
    public void checkEmptyInputField_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> FindCommandParserUtil.checkEmptyInputField(null, "input"));
    }

    @Test
    public void handleSearchByTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> FindCommandParserUtil.handleSearchByTag(null));
    }

    @Test
    public void handleSearchByDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> FindCommandParserUtil.handleSearchByDescription(null));
    }
}
