package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.getTypicalTags;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagsUtil;
import seedu.address.testutil.TypicalIndexes;

public class TagCommandParserTest {

    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parseCommand_tagAdd() throws Exception {
        Set<Tag> tags = getTypicalTags();
        List<Index> indexes = TypicalIndexes.VALID_INDEXES;
        AddTagCommand command = (AddTagCommand) parser.parse(AddTagCommand.ADD_SUB_COMMAND_WORD + " "
                + TypicalIndexes.VALID_INDEXES_STRING + " " + TagsUtil.getTagsDetails(tags));
        assertEquals(AddTagCommand.createWithTargetIndexes(indexes, tags), command);
    }

    @Test
    public void parseCommand_tagDelete() throws Exception {
        Set<Tag> tags = getTypicalTags();
        List<Index> indexes = TypicalIndexes.VALID_INDEXES;
        DeleteTagCommand command = (DeleteTagCommand) parser.parse(DeleteTagCommand.DELETE_SUB_COMMAND_WORD + " "
                + TypicalIndexes.VALID_INDEXES_STRING + " " + TagsUtil.getTagsDetails(tags));
        assertEquals(DeleteTagCommand.createWithTargetIndexes(indexes, tags), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                TagCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_COMMAND), () -> parser.parse(
                TagCommand.COMMAND_WORD + " unknownSubCommand"));
    }

    @Test
    public void parse_validTagCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // alias add
        assertValidCommandToAliasSuccess(parser, TagCommand.ADD_SUB_COMMAND_WORD);

        // alias delete
        assertValidCommandToAliasSuccess(parser, TagCommand.DELETE_SUB_COMMAND_WORD);
    }

    @Test
    public void parse_invalidTagCommandAlias_returnsFalse() {
        // invalid alias sub command
        assertValidCommandToAliasFailure(parser, INVALID_COMMAND);
    }

}
