package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.model.task.TaskContainsKeywordPredicate;

public class FindTasksCommandParserTest {
    private FindTasksCommandParser parser = new FindTasksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTasksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTasksCommand() {
        // no leading and trailing whitespaces
        FindTasksCommand expectedFindTasksCommand =
                new FindTasksCommand(new TaskContainsKeywordPredicate(Arrays.asList("book", "CS2103tp")));
        assertParseSuccess(parser, "book CS2103tp", expectedFindTasksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n book \n \t CS2103tp  \t", expectedFindTasksCommand);
    }
}
