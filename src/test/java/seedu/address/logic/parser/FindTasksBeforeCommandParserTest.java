package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTasksBeforeCommand;
import seedu.address.model.task.DeadlineBeforeDatePredicate;

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
