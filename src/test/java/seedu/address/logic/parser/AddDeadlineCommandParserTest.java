package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX_STANDALONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddDeadlineCommand;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.DeadlineBuilder;

public class AddDeadlineCommandParserTest {

    private AddDeadlineCommandParser parser = new AddDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Deadline expectedDeadline = new DeadlineBuilder().withDescription("CS2106 Tutorial")
                .withByDate(LocalDate.of(2020, 01, 01)).build();

        // all field appear
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION + VALID_DEADLINE_DATE,
                new AddDeadlineCommand(expectedProjectIndex, expectedDeadline)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, VALID_DESCRIPTION + VALID_DEADLINE_DATE, expectedMessage);

        // missing description
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DEADLINE_DATE, expectedMessage);

        // missing date
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, INVALID_INDEX_STANDALONE + VALID_DESCRIPTION + VALID_DEADLINE_DATE,
                MESSAGE_INVALID_INDEX);

        // invalid description
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_DESCRIPTION + VALID_DEADLINE_DATE,
                Messages.MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION + INVALID_DEADLINE_DATE,
                Messages.MESSAGE_PARSER_DATE_CONSTRAINTS);
    }
}
