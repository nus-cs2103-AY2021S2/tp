package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHTAGE_DESC_NAN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHTAGE_DESC_OOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHTAGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHTAGE_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.AMY;
import static seedu.address.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleName;
import seedu.address.model.person.Task;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // remarks are empty by default
        Task expectedTask = new TaskBuilder(BOB).withCode(VALID_CODE_BOB).withWeightage(50).withRemark("")
            .withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
            .withStatus("").withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + CODE_DESC_BOB + WEIGHTAGE_DESC_BOB
                + DATE_DESC_AMY + TIME_DESC_AMY
            + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + CODE_DESC_BOB + WEIGHTAGE_DESC_BOB
                + DATE_DESC_AMY + TIME_DESC_AMY
            + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + CODE_DESC_BOB + WEIGHTAGE_DESC_BOB
                + DATE_DESC_AMY + TIME_DESC_AMY + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        // remarks are empty by default
        Task expectedTaskMultipleTags = new TaskBuilder(BOB)
            .withCode(VALID_CODE_BOB)
            .withWeightage(50)
            .withRemark("")
            .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .build();

        assertParseSuccess(parser, NAME_DESC_BOB + CODE_DESC_BOB + DATE_DESC_AMY + TIME_DESC_AMY + WEIGHTAGE_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        // remarks are empty by default

        Task expectedTask = new TaskBuilder(AMY).withWeightage(25).withDeadlineDate("10-10-2020").withDeadlineTime("10"
                + ":10")
                .withStatus("").withRemark("").withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + CODE_DESC_AMY + WEIGHTAGE_DESC_AMY + DATE_DESC_AMY + TIME_DESC_AMY,
            new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + CODE_DESC_BOB + DATE_DESC_BOB + WEIGHTAGE_DESC_BOB
                + TIME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ModuleName.MESSAGE_CONSTRAINTS);

        // invalid code
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_CODE_DESC + DATE_DESC_BOB + WEIGHTAGE_DESC_BOB
                + TIME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid weightage - number out of bounds
        assertParseFailure(parser, NAME_DESC_BOB + CODE_DESC_BOB + DATE_DESC_BOB + INVALID_WEIGHTAGE_DESC_OOB
                + TIME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Weightage.MESSAGE_CONSTRAINTS);

        // invalid weightage - not a number
        assertParseFailure(parser, NAME_DESC_BOB + CODE_DESC_BOB + DATE_DESC_BOB + INVALID_WEIGHTAGE_DESC_NAN
                + TIME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ParserUtil.MESSAGE_INVALID_WEIGHTAGE);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + CODE_DESC_BOB + DATE_DESC_BOB + WEIGHTAGE_DESC_BOB
            + TIME_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + CODE_DESC_BOB + DATE_DESC_BOB + WEIGHTAGE_DESC_BOB
                        + TIME_DESC_BOB, ModuleName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CODE_DESC_BOB + NAME_DESC_BOB + WEIGHTAGE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
