package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.FindTasksCommand;
import seedu.heymatez.model.task.TaskContainsKeywordPredicate;

/**
 * Contains unit tests for {@code FindTasksCommandParser}.
 */
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
