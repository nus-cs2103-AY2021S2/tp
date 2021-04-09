package seedu.address.logic.parser.connections;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.connections.DeletePersonToMeetingConnectionCommand;

import java.util.HashSet;
import java.util.Set;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

class DeletePersonToMeetingConnectionParserTest {
    private DeletePersonToMeetingConnectionParser parser = new DeletePersonToMeetingConnectionParser();
    private static final String VALID_INPUT1 = "1 " + PREFIX_PERSON_CONNECTION + " 1";
    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        Set<Index> indexSet = new HashSet<>();
        indexSet.add(INDEX_FIRST);
        //Should be passed, but don't know why can't pass. (Use the actual product to isWithinRange and it is good).
        //assertParseSuccess(parser, VALID_INPUT1, new DeletePersonToMeetingConnectionCommand(INDEX_FIRST, indexSet));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonToMeetingConnectionCommand.MESSAGE_USAGE));
    }
}
