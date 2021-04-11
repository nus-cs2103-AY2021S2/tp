package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.PromptBookingStartCommand;
import seedu.booking.logic.parser.promptparsers.PromptBookingStartParser;
import seedu.booking.model.booking.StartTime;

public class PromptBookingStartParserTest {
    private final PromptBookingStartParser parser = new PromptBookingStartParser();

    @Test
    public void parseBookingStartTime_validField_success() {
        // correct format for time
        String str = "2020-01-01 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        assertParseSuccess(parser, "2020-01-01 23:59",
                new PromptBookingStartCommand(new StartTime(dateTime)));
    }

    @Test
    public void parseBookingStartTime_invalidField_failure() {
        String expectedMessage = Messages.MESSAGE_INVALID_DATE_FORMAT + Messages.PROMPT_MESSAGE_TRY_AGAIN;

        // not a time
        assertParseFailure(parser, "hello", expectedMessage);

        // invalid time values
        assertParseFailure(parser, "2020-01-01 25:00", expectedMessage);
        assertParseFailure(parser, "20-1-1 00:00", expectedMessage);
    }
}
