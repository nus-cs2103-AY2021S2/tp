package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EXTRA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTeachingAssistant.CONSULTATION;
import static seedu.address.testutil.TypicalTeachingAssistant.EXTRA_CLASS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEntryCommand;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EntryBuilder;

/**
 * Contains tests to make sure the parser instantiates the correct {@code AddEntryCommand}.
 */
public class AddEntryCommandParserTest {

    private final AddEntryCommandParser parser = new AddEntryCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Entry expectedEntry = new EntryBuilder(EXTRA_CLASS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA
                + TAG_DESC_EXTRA, new AddEntryCommand(expectedEntry));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_CONSULTATION + NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA
                + TAG_DESC_EXTRA, new AddEntryCommand(expectedEntry));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_EXTRA + START_DESC_CONSULTATION + START_DESC_EXTRA + END_DESC_EXTRA
                + TAG_DESC_EXTRA, new AddEntryCommand(expectedEntry));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_CONSULTATION + END_DESC_EXTRA
                + TAG_DESC_EXTRA, new AddEntryCommand(expectedEntry));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA
                + TAG_DESC_EXTRA, new AddEntryCommand(expectedEntry));

        // multiple tags - all accepted
        Entry expectedEntryMultipleTags = new EntryBuilder(EXTRA_CLASS)
                .withTags(VALID_TAG_CONSULTATION, VALID_TAG_EXTRA)
                .build();
        assertParseSuccess(parser, NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA
                + TAG_DESC_CONSULTATION + TAG_DESC_EXTRA, new AddEntryCommand(expectedEntryMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Entry expectedEntry = new EntryBuilder(CONSULTATION).withTags().build();
        assertParseSuccess(parser, NAME_DESC_CONSULTATION + START_DESC_CONSULTATION + END_DESC_CONSULTATION,
                new AddEntryCommand(expectedEntry));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEntryCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_EXTRA + VALID_START_DATE_EXTRA + END_DESC_EXTRA,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_EXTRA + START_DESC_EXTRA + VALID_END_DATE_EXTRA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_EXTRA + VALID_START_DATE_EXTRA + VALID_END_DATE_EXTRA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String invalidUserInput = "INVALID_NAME_DESC + START_DESC_EXTRA + END_DESC_EXTRA + TAG_DESC_EXTRA";

        // invalid name
        String expectedMsg = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEntryCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidUserInput, expectedMsg);

        // invalid start date
        expectedMsg = String.format("Date given: %s\n%s",
                INVALID_START_DATE.substring(4), EntryDate.DATE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_EXTRA + INVALID_START_DATE + END_DESC_EXTRA + TAG_DESC_CONSULTATION
                + TAG_DESC_EXTRA, expectedMsg);

        // invalid end date
        expectedMsg = String.format("Date given: %s\n%s",
                INVALID_END_DATE.substring(4), EntryDate.DATE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_EXTRA + START_DESC_EXTRA + INVALID_END_DATE + TAG_DESC_CONSULTATION
                + TAG_DESC_EXTRA, expectedMsg);

        // invalid tag
        expectedMsg = String.format("Tag given: %s\n%s",
                INVALID_TAG_DESC.substring(3) + "Math", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA
                + INVALID_TAG_DESC + VALID_TAG_EXTRA, expectedMsg);

        // invalid entry name
        expectedMsg = String.format("Name given: %s\n%s",
                INVALID_NAME_DESC.substring(3), EntryName.NAME_CONSTRAINTS);
        assertParseFailure(parser, INVALID_NAME_DESC + START_DESC_EXTRA + END_DESC_EXTRA, expectedMsg);

        // non-empty preamble
        expectedMsg = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEntryCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_EXTRA + START_DESC_EXTRA + END_DESC_EXTRA
                + TAG_DESC_CONSULTATION + TAG_DESC_EXTRA, expectedMsg);
    }
}

