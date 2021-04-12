package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CONSULTATION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EXTRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EXTRA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndices.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndices.INDEX_THIRD;
import static seedu.address.testutil.TypicalTeachingAssistant.CONSULTATION;
import static seedu.address.testutil.TypicalTeachingAssistant.EXTRA_CLASS;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEntryCommand;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.entry.TemporaryEntry;
import seedu.address.model.tag.Tag;

/**
 * Contains tests to make sure the parser instantiates the correct {@code EditEntryCommand}.
 */
public class EditEntryCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEntryCommand.MESSAGE_USAGE);

    private final EditEntryCommandParser parser = new EditEntryCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified: eedit n/event
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified: eedit event
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no name and no field specified: eedit
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser,
                "1" + INVALID_NAME_DESC,
                "Name given: James&\n" + EntryName.NAME_CONSTRAINTS); // invalid name
        assertParseFailure(parser,
                "1" + INVALID_START_DATE,
                "Date given: 2020-02-30 10:00\n" + EntryDate.DATE_CONSTRAINTS); // invalid date
        assertParseFailure(parser,
                "1" + INVALID_END_DATE,
                "Date given: 2021-10-10 24:30\n" + EntryDate.DATE_CONSTRAINTS); // invalid date
        assertParseFailure(parser,
                "1" + INVALID_TAG_DESC,
                "Tag given: hubby*\n" + Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Entry} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
                "1" + TAG_DESC_CONSULTATION + TAG_DESC_EXTRA + TAG_EMPTY,
                "Tag given: \n" + Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + TAG_DESC_CONSULTATION + TAG_EMPTY + TAG_DESC_EXTRA,
                "Tag given: \n" + Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + TAG_EMPTY + TAG_DESC_CONSULTATION + TAG_DESC_EXTRA,
                "Tag given: \n" + Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_END_DATE + VALID_START_DATE_EXTRA,
                "Name given: James&\n" + EntryName.NAME_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = "1" + NAME_DESC_CONSULTATION + START_DESC_CONSULTATION
                + END_DESC_CONSULTATION + TAG_DESC_CONSULTATION;

        TemporaryEntry tempEntry = new TemporaryEntry()
                .setEntryName(CONSULTATION.getEntryName())
                .setEntryStartDate(CONSULTATION.getOriginalStartDate())
                .setEntryEndDate(CONSULTATION.getOriginalEndDate())
                .setTags(CONSULTATION.getTags());
        EditEntryCommand expectedCommand = new EditEntryCommand(INDEX_FIRST, tempEntry);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = "1" + NAME_DESC_CONSULTATION + END_DESC_EXTRA;

        TemporaryEntry tempEntry = new TemporaryEntry()
                .setEntryName(CONSULTATION.getEntryName())
                .setEntryEndDate(EXTRA_CLASS.getOriginalEndDate());
        EditEntryCommand expectedCommand = new EditEntryCommand(INDEX_FIRST, tempEntry);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD;

        // name
        String userInput = targetIndex.getOneBased() + NAME_DESC_CONSULTATION;
        TemporaryEntry tempEntry = new TemporaryEntry().setEntryName(CONSULTATION.getEntryName());
        EditEntryCommand expectedCommand = new EditEntryCommand(targetIndex, tempEntry);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + START_DESC_CONSULTATION;
        tempEntry = new TemporaryEntry().setEntryStartDate(CONSULTATION.getOriginalStartDate());
        expectedCommand = new EditEntryCommand(targetIndex, tempEntry);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end date
        userInput = targetIndex.getOneBased() + END_DESC_CONSULTATION;
        tempEntry = new TemporaryEntry().setEntryEndDate(CONSULTATION.getOriginalEndDate());
        expectedCommand = new EditEntryCommand(targetIndex, tempEntry);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CONSULTATION;
        tempEntry = new TemporaryEntry().setTags(CONSULTATION.getTags());
        expectedCommand = new EditEntryCommand(targetIndex, tempEntry);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = "1" + NAME_DESC_EXTRA + NAME_DESC_CONSULTATION;

        TemporaryEntry tempEntry = new TemporaryEntry().setEntryName(CONSULTATION.getEntryName());
        EditEntryCommand expectedCommand = new EditEntryCommand(INDEX_FIRST, tempEntry);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValuesPresent_throwsIllegalArgumentException() {
        // invalid field
        String userInput = "1" + INVALID_NAME_DESC + START_DESC_EXTRA;
        assertParseFailure(parser, userInput, "Name given: James&\n" + EntryName.NAME_CONSTRAINTS);

        // still detects invalid value despite a valid field exists
        userInput = "1" + NAME_DESC_EXTRA + INVALID_NAME_DESC + END_DESC_EXTRA;
        assertParseFailure(parser, userInput, "Name given: James&\n" + EntryName.NAME_CONSTRAINTS);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = "1" + TAG_EMPTY;

        TemporaryEntry tempEntry = new TemporaryEntry()
                .setTags(new HashSet<>());
        EditEntryCommand expectedCommand = new EditEntryCommand(INDEX_FIRST, tempEntry);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
