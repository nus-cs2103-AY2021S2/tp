package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DueInCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DeadlineDateInRangePredicate;

public class DueInCommandParserTest {

    private static final long DEFAULT_NUMBER_OF_DAYS = 7;

    private DueInCommandParser parser = new DueInCommandParser();

    @Test
    public void parse_emptyArg_returnsDueInCommand() {
        DueInCommand expectedDueInCommand;
        try {
            expectedDueInCommand =
                    new DueInCommand(new DeadlineDateInRangePredicate(DEFAULT_NUMBER_OF_DAYS));
        } catch (ParseException e) {
            assertFalse(true); // Must not be called
            return;
        }
        assertParseSuccess(parser, "", expectedDueInCommand);
        assertParseSuccess(parser, " ", expectedDueInCommand); // single whitespaces
        assertParseSuccess(parser, "         ", expectedDueInCommand); // multiple whitespaces
    }

    @Test
    public void parse_invalidArgs_returnsDueInCommand() {
        DueInCommand expectedDueInCommand;
        try {
            expectedDueInCommand =
                    new DueInCommand(new DeadlineDateInRangePredicate(DEFAULT_NUMBER_OF_DAYS));
        } catch (ParseException e) {
            assertFalse(true); // Must not be called
            return;
        }
        assertParseSuccess(parser, "   sdadasdas ", expectedDueInCommand); // random
        assertParseSuccess(parser, " days/20", expectedDueInCommand); // typo
        assertParseSuccess(parser, " weeks/20", expectedDueInCommand); // typo
    }

    @Test
    public void parse_invalidArgs_throwsError() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueInCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "  day/", expectedMessage); // empty argument
        assertParseFailure(parser, "  week/", expectedMessage); // empty argument
        assertParseFailure(parser, "  day/ashiap", expectedMessage); // invalid input for day param
        assertParseFailure(parser, "  week/ashiap", expectedMessage); // invalid input for week param
        assertParseFailure(parser, "  day/-1", expectedMessage); // invalid input for day param
        assertParseFailure(parser, "  week/-1", expectedMessage); // invalid input for week param
        assertParseFailure(parser, "  day/0", expectedMessage); // invalid input for day param
        assertParseFailure(parser, "  week/0", expectedMessage); // invalid input for week param
        assertParseFailure(parser, "  day/999999999999999", expectedMessage); // invalid input for day param
        assertParseFailure(parser, "  week/999999999999999", expectedMessage); // invalid input for week param
    }

    @Test
    public void parse_validArgs_returnsDueInCommand() {
        DueInCommand expectedDueInCommand;
        try {
            expectedDueInCommand =
                    new DueInCommand(new DeadlineDateInRangePredicate(DEFAULT_NUMBER_OF_DAYS));
        } catch (ParseException e) {
            assertFalse(true); // Must not be called
            return;
        }

        // day given
        assertParseSuccess(parser, " day/7", expectedDueInCommand);
        assertParseSuccess(parser, " day/7              ", expectedDueInCommand); //whitespaces behind
        assertParseSuccess(parser, "         day/7", expectedDueInCommand); //whitespaces in front
        assertParseSuccess(parser, "      day/7    ", expectedDueInCommand); //whitespaces in both side
        assertParseSuccess(parser, "  day/10   day/7    ", expectedDueInCommand); //multiple day params


        // week given
        assertParseSuccess(parser, " week/1", expectedDueInCommand);
        assertParseSuccess(parser, " week/1              ", expectedDueInCommand); //whitespaces behind
        assertParseSuccess(parser, "         week/1", expectedDueInCommand); //whitespaces in front
        assertParseSuccess(parser, "      week/1    ", expectedDueInCommand); //whitespaces in both side
        assertParseSuccess(parser, "   week/10   week/1    ", expectedDueInCommand); //multiple week params
    }

    @Test
    public void parse_multipleParams_throwsError() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueInCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " day/7 week/20", expectedMessage); // both valid args given
        assertParseFailure(parser, " day/7 week/", expectedMessage); // one valid and one empty
        assertParseFailure(parser, " day/ week/", expectedMessage); // both empty
        assertParseFailure(parser, " day/ week/7", expectedMessage); // one valid and one empty
        assertParseFailure(parser, " day/sada week/7", expectedMessage); // one valid and one invalid
        assertParseFailure(parser, " day/7 week/sdadas", expectedMessage); // one valid and one invalid
        assertParseFailure(parser, " day/adsad week/asdsdad", expectedMessage); // both invalid
    }

}
