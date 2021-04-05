package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.FindTasksBeforeCommand;
import seedu.heymatez.model.task.DeadlineBeforeDatePredicate;

/**
 * Contains unit tests for {@code FindTasksBeforeCommandParser}.
 */
public class FindTasksBeforeCommandParserTest {
    private FindTasksBeforeCommandParser parser = new FindTasksBeforeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTasksBeforeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTasksBeforeCommand() {
        // no leading and trailing whitespaces
        FindTasksBeforeCommand expectedFindTasksBeforeCommand =
                new FindTasksBeforeCommand(new DeadlineBeforeDatePredicate("2021-05-06"));
        assertParseSuccess(parser, "2021-05-06", expectedFindTasksBeforeCommand);

        // multiple whitespaces
        assertParseSuccess(parser, " 2021-05-06  ", expectedFindTasksBeforeCommand);
    }
}
