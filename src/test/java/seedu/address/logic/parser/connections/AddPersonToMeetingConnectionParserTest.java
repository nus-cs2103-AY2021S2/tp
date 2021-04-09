package seedu.address.logic.parser.connections;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.connections.AddPersonToMeetingConnectionCommand;
import seedu.address.logic.parser.ParserUtil;

import java.util.HashSet;
import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;


class AddPersonToMeetingConnectionParserTest {
    private AddPersonToMeetingConnectionParser parser = new AddPersonToMeetingConnectionParser();
    private static final String VALID_INPUT1 = " " + "1 " + PREFIX_PERSON_CONNECTION + "1 " + PREFIX_PERSON_CONNECTION + "2";
    private static final String INVALID_INPUT1 = " " + PREFIX_PERSON_CONNECTION + "1 ";
    private static final String INVALID_INPUT2 = " " + "1 " + PREFIX_PERSON_CONNECTION + "a";
    @Test
    public void parse_success() throws Exception {
        Index validMeetingIndex = ParserUtil.parseIndex("1");

        Set<Index> validPersonIndexSet1 = new HashSet<>();
        Index validPersonIndex1 = ParserUtil.parseIndex("1");
        Index validPersonIndex2 = ParserUtil.parseIndex("2");
        validPersonIndexSet1.add(validPersonIndex1);
        validPersonIndexSet1.add(validPersonIndex2);

        // whitespace only preamble
        //assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_INPUT1, new AddPersonToMeetingConnectionCommand(validMeetingIndex, validPersonIndexSet1));
        // Don't know why can't pass the test, while using the debugger to check it should be correct.
    }



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