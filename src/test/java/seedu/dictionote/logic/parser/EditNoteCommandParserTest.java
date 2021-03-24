package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.commands.CommandTestUtil.NOTE_DESC;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NOTE_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.EditNoteCommand;
import seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.dictionote.testutil.EditNoteDescriptorBuilder;

public class EditNoteCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE);

    private EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NOTE_CONTENT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditNoteCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_NOTE_CONTENT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_NOTE_CONTENT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_NOTE;
        String userInput = targetIndex.getOneBased() + NOTE_DESC;

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder()
                .withNote(VALID_NOTE_CONTENT).build();
        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_FIRST_NOTE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder().withTags().build();
        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
