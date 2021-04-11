package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_DATE_CONSTRAINTS;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_TIME_CONSTRAINTS;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_WEEKLY_CONSTRAINTS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_WEEKLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_WEEKLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateEventCommand;

public class UpdateEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateEventCommand.MESSAGE_USAGE);

    private UpdateEventCommandParser parser = new UpdateEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no event index specified
        assertParseFailure(parser, "1" + VALID_EVENT_DATE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateEventCommand.MESSAGE_USAGE));

        // no project index specified
        assertParseFailure(parser, "i/1" + VALID_DESCRIPTION, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateEventCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + " i/1" + VALID_DESCRIPTION, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + " i/1" + VALID_DESCRIPTION, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + " i/1" + VALID_DESCRIPTION,
                MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidEventIndex_failure() {
        // negative index
        assertParseFailure(parser, VALID_INDEX_ONE + " i/-5" + VALID_DESCRIPTION,
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, VALID_INDEX_ONE + " i/0" + VALID_DESCRIPTION,
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1 some random string" + VALID_DESCRIPTION,
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidField_failure() {
        // description
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1" + INVALID_DESCRIPTION,
                MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS);

        // date
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1" + INVALID_EVENT_DATE,
                MESSAGE_PARSER_DATE_CONSTRAINTS);

        // time
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1" + INVALID_EVENT_TIME,
                MESSAGE_PARSER_TIME_CONSTRAINTS);

        // weekly
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1" + INVALID_EVENT_WEEKLY,
                MESSAGE_PARSER_WEEKLY_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldSpecifiedAndValid_success() {
        String userInput = VALID_INDEX_ONE + " i/1" + VALID_DESCRIPTION + VALID_EVENT_DATE
                + VALID_EVENT_TIME + VALID_EVENT_WEEKLY;
        UpdateEventCommand.UpdateEventDescriptor descriptor = new UpdateEventCommand.UpdateEventDescriptor();
        descriptor.setDescription("CS2106 Tutorial");
        descriptor.setDate(LocalDate.of(2020, 1, 1));
        descriptor.setTime(LocalTime.of(17, 30));
        descriptor.setIsWeekly(false);

        UpdateEventCommand expectedCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        String userInput = VALID_INDEX_ONE + " i/1" + VALID_DESCRIPTION;
        UpdateEventCommand.UpdateEventDescriptor descriptor = new UpdateEventCommand.UpdateEventDescriptor();
        descriptor.setDescription("CS2106 Tutorial");
        UpdateEventCommand expectedCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = VALID_INDEX_ONE + " i/1" + VALID_EVENT_DATE;
        descriptor = new UpdateEventCommand.UpdateEventDescriptor();
        descriptor.setDate(LocalDate.of(2020, 1, 1));
        expectedCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = VALID_INDEX_ONE + " i/1" + VALID_EVENT_TIME;
        descriptor = new UpdateEventCommand.UpdateEventDescriptor();
        descriptor.setTime(LocalTime.of(17, 30));
        expectedCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = VALID_INDEX_ONE + " i/1" + VALID_EVENT_WEEKLY;
        descriptor = new UpdateEventCommand.UpdateEventDescriptor();
        descriptor.setIsWeekly(false);
        expectedCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
