package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMemberTasksCommand;
import seedu.address.model.assignee.Assignee;
import seedu.address.model.task.TaskContainsAssigneePredicate;

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
        assertParseFailure(parser, "Alex Yeoh /t", Assignee.MESSAGE_CONSTRAINTS);
    }
}
