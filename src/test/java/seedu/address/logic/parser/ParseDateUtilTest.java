package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ParseDateUtilTest {
    private static final String INCORRECT_DATE_STRING = "22-09-04 7890";
    private static final String CORRECT_DATE_STRING = "2020-01-12 23:59";
    private static final LocalDateTime CORRECT_LOCAL_DATETIME =
            LocalDateTime.of(2020, Month.JANUARY, 12, 23, 59, 0);

    @Test
    public void formatDateTime_validValue_returnsFormattedString() {
        String formattedDateTime = ParseDateUtil.formatDateTime(CORRECT_LOCAL_DATETIME);
        assertEquals(formattedDateTime, CORRECT_DATE_STRING);
    }

    @Test
    public void parseDateTime_validValue_returnsLocalDateTime() throws ParseException {
        LocalDateTime localDateTime = ParseDateUtil.parseDateTime(CORRECT_DATE_STRING);
        assertEquals(localDateTime, CORRECT_LOCAL_DATETIME);
    }
    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, ()-> {
            ParseDateUtil.parseDateTime(INCORRECT_DATE_STRING);
        });

    }

}
