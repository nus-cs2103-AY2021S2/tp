package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetGoalCommand;
import seedu.address.model.person.Goal;

public class SetGoalCommandParserTest {

    private static final SetGoalCommandParser PARSER = new SetGoalCommandParser();

    @Test
    public void parse_emptyArg_throwParseException() {
        assertParseFailure(PARSER, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGoalCommand.MESSAGE_USAGE));
        assertParseFailure(PARSER, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGoalCommand.MESSAGE_USAGE));
        assertParseFailure(PARSER, "\t", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGoalCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validGoal_success() {
        SetGoalCommand expectedSetGoalCommand = new SetGoalCommand(Index.fromOneBased(1), Goal.Frequency.WEEKLY);
        assertParseSuccess(PARSER, "1 f/w", expectedSetGoalCommand);
        assertParseSuccess(PARSER, "1 f/week", expectedSetGoalCommand);
        assertParseSuccess(PARSER, "1 f/weekly", expectedSetGoalCommand);

        expectedSetGoalCommand = new SetGoalCommand(Index.fromOneBased(1), Goal.Frequency.MONTHLY);
        assertParseSuccess(PARSER, "1 f/m", expectedSetGoalCommand);
        assertParseSuccess(PARSER, "1 f/month", expectedSetGoalCommand);
        assertParseSuccess(PARSER, "1 f/monthly", expectedSetGoalCommand);

        expectedSetGoalCommand = new SetGoalCommand(Index.fromOneBased(1), Goal.Frequency.YEARLY);
        assertParseSuccess(PARSER, "1 f/y", expectedSetGoalCommand);
        assertParseSuccess(PARSER, "1 f/year", expectedSetGoalCommand);
        assertParseSuccess(PARSER, "1 f/yearly", expectedSetGoalCommand);

        // ignore case
        assertParseSuccess(PARSER, "1 f/yeArLy", expectedSetGoalCommand);

        // ignore whitespace
        assertParseSuccess(PARSER, "1 f/ Y   ", expectedSetGoalCommand);
    }

    @Test
    public void parse_invalidFrequency_failure() {
        assertParseFailure(PARSER, "1 f/asd", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetGoalCommand.MESSAGE_USAGE));
        assertParseFailure(PARSER, "1 f/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetGoalCommand.MESSAGE_USAGE));
        assertParseFailure(PARSER, "1 f/    ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetGoalCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noFrequency_failure() {
        assertParseFailure(PARSER, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetGoalCommand.MESSAGE_USAGE));
    }
}
