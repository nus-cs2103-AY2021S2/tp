package seedu.address.logic.parser.connections;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.connections.AddPersonToMeetingConnectionCommand;


class AddPersonToMeetingConnectionParserTest {
    private static final String VALID_INPUT1 = " " + "1 " + PREFIX_PERSON_CONNECTION
            + "1 " + PREFIX_PERSON_CONNECTION + "2";
    private static final String INVALID_INPUT1 = " " + PREFIX_PERSON_CONNECTION + "1 ";
    private static final String INVALID_INPUT2 = " " + "1 " + PREFIX_PERSON_CONNECTION + "a";
    private AddPersonToMeetingConnectionParser parser = new AddPersonToMeetingConnectionParser();
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage1 = "Invalid command format! \n" + AddPersonToMeetingConnectionCommand.MESSAGE_USAGE;


        // missing meeting index
        assertParseFailure(parser, INVALID_INPUT1, expectedMessage1);

    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid index input
        String expectedMessage = "Index of a person or a meeting is not a non-zero unsigned integer.";

        assertParseFailure(parser, INVALID_INPUT2, expectedMessage);

    }
}
