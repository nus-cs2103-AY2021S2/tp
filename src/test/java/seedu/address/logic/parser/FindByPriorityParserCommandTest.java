package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByPriorityCommand;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityContainsKeywordPredicate;

public class FindByPriorityParserCommandTest {
    private FindByPriorityCommandParser parser = new FindByPriorityCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTasksCommand() {
        // no leading and trailing whitespaces
        FindByPriorityCommand expectedTasksByPriorityCommand =
                new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList("high")));

        assertParseSuccess(parser, "high", expectedTasksByPriorityCommand);

        expectedTasksByPriorityCommand =
                new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList("low")));

        assertParseSuccess(parser, "low", expectedTasksByPriorityCommand);

        expectedTasksByPriorityCommand =
                new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList("medium")));

        assertParseSuccess(parser, "medium", expectedTasksByPriorityCommand);

        expectedTasksByPriorityCommand =
                new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList("unassigned")));

        assertParseSuccess(parser, "unassigned", expectedTasksByPriorityCommand);
    }

    @Test
    public void parse_invalidArgs_returnsFindTasksCommand() {

        assertParseFailure(parser, "low high", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByPriorityCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "extreme", String.format(Priority.MESSAGE_CANNOT_FIND_PRIORITY,
                FindByPriorityCommand.MESSAGE_USAGE));
    }
}
