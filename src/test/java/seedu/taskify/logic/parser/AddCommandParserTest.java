package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.commands.CommandTestUtil.DATE_DESC_CS2103T_IP;
import static seedu.taskify.logic.commands.CommandTestUtil.DATE_DESC_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2103T_IP;
import static seedu.taskify.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.NAME_DESC_CS2103T_IP;
import static seedu.taskify.logic.commands.CommandTestUtil.NAME_DESC_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taskify.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taskify.logic.commands.CommandTestUtil.TAG_DESC_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.TAG_DESC_DEBUGGING;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_DEBUGGING;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskify.testutil.TypicalTasks.BOB;
import static seedu.taskify.testutil.TypicalTasks.CS2103T_IP;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.taskify.logic.commands.AddCommand;
import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Description;
import seedu.taskify.model.task.Name;
import seedu.taskify.model.task.Task;
import seedu.taskify.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_CS2103T_TP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                        + DATE_DESC_CS2103T_TP + TAG_DESC_CS2103T_TP, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_CS2103T_IP + NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                         + DATE_DESC_CS2103T_TP + TAG_DESC_CS2103T_TP, new AddCommand(expectedTask));

        // multiple description - last description accepted
        assertParseSuccess(parser, NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_IP + DESCRIPTION_DESC_CS2103T_TP
                         + DATE_DESC_CS2103T_TP + TAG_DESC_CS2103T_TP, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB)
                .withTags(VALID_TAG_CS2103T_TP, VALID_TAG_DEBUGGING).build();
        assertParseSuccess(parser, NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                        + DATE_DESC_CS2103T_TP + TAG_DESC_DEBUGGING + TAG_DESC_CS2103T_TP,
                                new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(CS2103T_IP).withTags().build();
        assertParseSuccess(parser, NAME_DESC_CS2103T_IP + DESCRIPTION_DESC_CS2103T_IP
                        + DATE_DESC_CS2103T_IP, new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                        + DATE_DESC_CS2103T_TP, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_CS2103T_TP + VALID_DESCRIPTION_CS2103T_TP
                        + DATE_DESC_CS2103T_TP, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_CS2103T_TP + VALID_DESCRIPTION_CS2103T_TP
                        + VALID_NAME_CS2103T_TP, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_CS2103T_TP
                        + DATE_DESC_CS2103T_TP + TAG_DESC_DEBUGGING + TAG_DESC_CS2103T_TP, Name.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_CS2103T_TP + INVALID_DESCRIPTION_DESC
                        + DATE_DESC_CS2103T_TP + TAG_DESC_DEBUGGING + TAG_DESC_CS2103T_TP,
                Description.MESSAGE_CONSTRAINTS);


        // invalid date
        assertParseFailure(parser, NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                        + INVALID_DATE_DESC + TAG_DESC_DEBUGGING + TAG_DESC_CS2103T_TP, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                        + DATE_DESC_CS2103T_TP + INVALID_TAG_DESC + VALID_TAG_CS2103T_TP, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_DESCRIPTION_DESC
                        + DATE_DESC_CS2103T_TP, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_CS2103T_TP + DESCRIPTION_DESC_CS2103T_TP
                         + DATE_DESC_CS2103T_TP + TAG_DESC_DEBUGGING + TAG_DESC_CS2103T_TP,
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    // If no date prefix is specified, a Task with today's date with time set to 23:59 should be initialized.
    public void parse_noDatePrefix_success() {
        String args = " n/TestName desc/TestDesc";
        String expectedDateString = LocalDate.now().toString() + " 23:59";
        Task expectedTask = new TaskBuilder().withName("TestName").withDescription("TestDesc")
                .withDate(expectedDateString).build();
        assertParseSuccess(parser, args, new AddCommand(expectedTask));
    }
}
