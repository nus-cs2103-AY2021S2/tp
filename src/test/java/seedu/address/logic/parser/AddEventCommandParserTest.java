package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_WEEKLY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX_STANDALONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_WEEKLY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.task.repeatable.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {

    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Event expectedEvent = new EventBuilder().withDescription("CS2106 Tutorial")
                .withDate(LocalDate.of(2020, 1, 1)).withTime(LocalTime.of(17, 30))
                .withIsWeekly(false).build();

        // all field appear
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                        + VALID_EVENT_DATE + VALID_EVENT_TIME + VALID_EVENT_WEEKLY,
                new AddEventCommand(expectedProjectIndex, expectedEvent)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, VALID_DESCRIPTION
                        + VALID_EVENT_DATE + VALID_EVENT_TIME + VALID_EVENT_WEEKLY,
                expectedMessage);

        // missing description
        assertParseFailure(parser, INDEX_STANDALONE_ONE
                        + VALID_EVENT_DATE + VALID_EVENT_TIME + VALID_EVENT_WEEKLY,
                expectedMessage);

        // missing date
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                        + VALID_EVENT_TIME + VALID_EVENT_WEEKLY,
                expectedMessage);

        // missing time
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                        + VALID_EVENT_DATE + VALID_EVENT_WEEKLY,
                expectedMessage);

        // missing isWeekly
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                        + VALID_EVENT_DATE + VALID_EVENT_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, INVALID_INDEX_STANDALONE + VALID_DESCRIPTION
                + VALID_EVENT_DATE + VALID_EVENT_TIME + VALID_EVENT_WEEKLY, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // invalid description
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_DESCRIPTION
                + VALID_EVENT_DATE + VALID_EVENT_TIME + VALID_EVENT_WEEKLY,
                Messages.MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                + INVALID_EVENT_DATE + VALID_EVENT_TIME + VALID_EVENT_WEEKLY, Messages.MESSAGE_PARSER_DATE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                + VALID_EVENT_DATE + INVALID_EVENT_TIME + VALID_EVENT_WEEKLY, Messages.MESSAGE_PARSER_TIME_CONSTRAINTS);

        // invalid weekly
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION
                + VALID_EVENT_DATE + VALID_EVENT_TIME + INVALID_EVENT_WEEKLY,
                Messages.MESSAGE_PARSER_WEEKLY_CONSTRAINTS);
    }
}
