package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.person.Event;
import seedu.address.testutil.EventBuilder;

public class AddMeetingCommandParserTest {
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    private final Event meeting1 = new EventBuilder()
            .withDate(LocalDate.of(2020, 12, 24))
            .withTime(LocalTime.of(12, 15))
            .withDescription("sample description")
            .build();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 d/24-12-2020 t/1215 desc/sample description",
                new AddMeetingCommand(Index.fromOneBased(1), meeting1));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, "d/24-12-2020 t/1215 desc/sample description", expectedMessage);

        // missing date
        assertParseFailure(parser, "1 t/1215 desc/sample description", expectedMessage);

        // missing time
        assertParseFailure(parser, "1 d/24-12-2020 desc/sample description", expectedMessage);

        // missing description
        assertParseFailure(parser, "1 d/24-12-2020 t/1215", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // wrong date format
        assertParseFailure(parser, "1 d/24 Dec 2020 t/1215 desc/sample description",
                DateUtil.MESSAGE_CONSTRAINT);

        // wrong time format
        assertParseFailure(parser, "1 d/24-12-2020 t/12:15pm desc/sample description",
                TimeUtil.MESSAGE_CONSTRAINT);

        // empty description
        assertParseFailure(parser, "1 d/24-12-2020 t/1215 desc/", Event.DESCRIPTION_MESSAGE_CONSTRAINTS);
    }
}
