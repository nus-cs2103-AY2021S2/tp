package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_PRIORITY;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.FindByPriorityCommand;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.PriorityContainsKeywordPredicate;

/**
 * Contains unit tests for {@code FindByPriorityCommandParser}.
 */
public class FindByPriorityCommandParserTest {
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

        assertParseFailure(parser, "extreme", MESSAGE_INVALID_TASK_PRIORITY
                + Priority.MESSAGE_CONSTRAINTS);
    }
}
