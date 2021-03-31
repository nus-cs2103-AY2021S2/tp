package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DueInCommand;
import seedu.address.model.person.DeadlineDateInRangePredicate;

public class DueInCommandParserTest {

    private static final long DEFAULT_NUMBER_OF_DAYS = 7;

    private DueInCommandParser parser = new DueInCommandParser();

    @Test
    public void parse_emptyArg_returnsDueInCommand() {
        DueInCommand expectedDueInCommand =
                new DueInCommand(new DeadlineDateInRangePredicate(DEFAULT_NUMBER_OF_DAYS));
        assertParseSuccess(parser, "", expectedDueInCommand);
        assertParseSuccess(parser, " ", expectedDueInCommand); // single whitespaces
        assertParseSuccess(parser, "         ", expectedDueInCommand); // multiple whitespaces
    }

    @Test
    public void parse_invalidArgs_returnsDueInCommand() {
        DueInCommand expectedDueInCommand =
                new DueInCommand(new DeadlineDateInRangePredicate(DEFAULT_NUMBER_OF_DAYS));
        assertParseSuccess(parser, "   sdadasdas ", expectedDueInCommand); // random
        assertParseSuccess(parser, " days/20", expectedDueInCommand); // typo
        assertParseSuccess(parser, " weeks/20", expectedDueInCommand); // typo
        assertParseSuccess(parser, " days/20 weeks/20", expectedDueInCommand); // typo
    }

    @Test
    public void parse_validArgs_returnsDueInCommand() {
        DueInCommand expectedDueInCommand =
                new DueInCommand(new DeadlineDateInRangePredicate(DEFAULT_NUMBER_OF_DAYS));

        // day given
        assertParseSuccess(parser, " day/7", expectedDueInCommand);
        assertParseSuccess(parser, " day/7              ", expectedDueInCommand); //whitespaces behind
        assertParseSuccess(parser, "         day/7", expectedDueInCommand); //whitespaces in front
        assertParseSuccess(parser, "      day/7    ", expectedDueInCommand); //whitespaces in both side


        // week given
        assertParseSuccess(parser, " week/1", expectedDueInCommand);
        assertParseSuccess(parser, " week/1              ", expectedDueInCommand); //whitespaces behind
        assertParseSuccess(parser, "         week/1", expectedDueInCommand); //whitespaces in front
        assertParseSuccess(parser, "      week/1    ", expectedDueInCommand); //whitespaces in both side

        // both given
        assertParseSuccess(parser, " day/7 week/20", expectedDueInCommand);
        //whitespaces behind
        assertParseSuccess(parser, " day/7 week/20              ", expectedDueInCommand);
        //whitespaces in front
        assertParseSuccess(parser, "         day/7 week/20", expectedDueInCommand);
        //whitespaces in both side
        assertParseSuccess(parser, "      day/7 week/20    ", expectedDueInCommand);
        //whitespaces between
        assertParseSuccess(parser, "    start/10-11-2022    end/10-11-2023", expectedDueInCommand);
    }

}
