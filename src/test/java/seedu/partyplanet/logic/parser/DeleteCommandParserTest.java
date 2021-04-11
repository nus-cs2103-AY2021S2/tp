package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.commands.DeleteClearCommand;
import seedu.partyplanet.logic.commands.DeleteCommand;
import seedu.partyplanet.logic.commands.DeleteContactCommand;
import seedu.partyplanet.logic.commands.DeleteContactWithTagCommand;
import seedu.partyplanet.model.tag.Tag;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_emptyArgs_returnsDeleteClearCommand() {
        assertParseSuccess(parser, "", new DeleteClearCommand());
    }

    @Test
    public void parse_singleValidIndex_returnsDeleteContactCommand() {
        assertParseSuccess(parser, " 1", new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of()));
    }

    @Test
    public void parse_multipleValidIndex_returnsDeleteContactCommand() {
        assertParseSuccess(parser, " 1 2",
                new DeleteContactCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), List.of()));
    }

    @Test
    public void parse_multipleValidAndInvalidIndex_returnsDeleteContactCommand() {
        assertParseSuccess(parser, " 1 2 -1 a",
                new DeleteContactCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), List.of("-1", "a")));
    }

    @Test
    public void parse_multipleDuplicatedValidAndInvalidIndex_returnsDeleteContactCommand() {
        assertParseSuccess(parser, " 1 1 1 -1 -1 -1",
                new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of("-1")));
    }

    @Test
    public void parse_singleValidTagAndIsAll_returnsDeleteContactWithTagCommand() {
        Set<Tag> tags = Set.of(new Tag("tag"));

        assertParseSuccess(parser, " -t tag", new DeleteContactWithTagCommand(tags, false));
    }

    @Test
    public void parse_multipleValidTagAndIsAll_returnsDeleteContactWithTagCommand() {
        Set<Tag> tags = Set.of(new Tag("one"), new Tag("two"));

        assertParseSuccess(parser, " -t one -t two", new DeleteContactWithTagCommand(tags, false));
    }

    @Test
    public void parse_multipleValidTagAndIsAny_returnsDeleteContactWithTagCommand() {
        Set<Tag> tags = Set.of(new Tag("one"), new Tag("two"));

        assertParseSuccess(parser, " --any -t one -t two", new DeleteContactWithTagCommand(tags, true));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " a a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " -1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_bothIndexAndTag_throwsParseException() {
        assertParseFailure(parser, " 1 -t tag",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_anyFlagWithoutTags_throwsParseException() {
        assertParseFailure(parser, " --any",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " 1 --any",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " --any 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTags_throwsParseException() {
        assertParseFailure(parser, " -t ", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " --any -t ", Tag.MESSAGE_CONSTRAINTS);
    }
}
