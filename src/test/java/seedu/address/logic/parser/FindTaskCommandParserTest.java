package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

public class FindTaskCommandParserTest {
    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindCommand =
                new FindTaskCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList("taskone", "tasktwo")));
        assertParseSuccess(parser, "taskone tasktwo", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n taskone \n \t tasktwo  \t", expectedFindCommand);
    }
}
