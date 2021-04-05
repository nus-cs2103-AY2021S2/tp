package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.FindMemberTasksCommand;
import seedu.heymatez.model.assignee.Assignee;
import seedu.heymatez.model.task.TaskContainsAssigneePredicate;

/**
 * Contains unit tests for {@code FindMemberTasksCommandParser}.
 */
public class FindMemberTasksCommandParserTest {
    private FindMemberTasksCommandParser parser = new FindMemberTasksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindMemberTasksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_returnsFindMemberTasksCommand() {
        // no leading and trailing whitespaces
        FindMemberTasksCommand expectedFindMemberTasksCommand =
                new FindMemberTasksCommand(new TaskContainsAssigneePredicate("Alice"));
        assertParseSuccess(parser, "Alice", expectedFindMemberTasksCommand);

        // multiple whitespaces between keywords
        assertParseFailure(parser, "Alex Yeoh /t", Assignee.MESSAGE_SEARCH_TASKS_CONSTRAINTS);
    }
}
