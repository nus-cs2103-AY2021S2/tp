package seedu.address.logic.parser.connections;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.connections.DeletePersonToMeetingConnectionCommand;

class DeletePersonToMeetingConnectionParserTest {
    private static final String VALID_INPUT1 = "1 " + PREFIX_PERSON_CONNECTION + " 1";
    private DeletePersonToMeetingConnectionParser parser = new DeletePersonToMeetingConnectionParser();
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonToMeetingConnectionCommand.MESSAGE_USAGE));
    }
}
