package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.commands.EDeleteClearCommand;
import seedu.partyplanet.logic.commands.EDeleteCommand;
import seedu.partyplanet.logic.commands.EDeleteEventCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the EDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the EDeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class EDeleteCommandParserTest {

    private EDeleteCommandParser parser = new EDeleteCommandParser();

    @Test
    public void parse_emptyArgs_returnsEDeleteClearCommand() {
        assertParseSuccess(parser, "", new EDeleteClearCommand());
    }

    @Test
    public void parse_singleValidIndex_returnsEDeleteEventCommand() {
        assertParseSuccess(parser, " 1", new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT), List.of()));
    }

    @Test
    public void parse_multipleValidIndex_returnsEDeleteEventCommand() {
        assertParseSuccess(parser, " 1 2",
                new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of()));
    }

    @Test
    public void parse_multipleValidAndInvalidIndex_returnsEDeleteEventCommand() {
        assertParseSuccess(parser, " 1 2 -1 a",
                new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT, INDEX_SECOND_EVENT), List.of("-1", "a")));
    }

    @Test
    public void parse_multipleDuplicatedValidAndInvalidIndex_returnsEDeleteEventCommand() {
        assertParseSuccess(parser, " 1 1 1 -1 -1 -1",
                new EDeleteEventCommand(List.of(INDEX_FIRST_EVENT), List.of("-1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, EDeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " a a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, EDeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " -1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, EDeleteCommand.MESSAGE_USAGE));
    }
}
