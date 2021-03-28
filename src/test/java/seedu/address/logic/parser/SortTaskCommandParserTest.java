package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortTaskCommand;
import seedu.address.model.task.TaskComparator;

public class SortTaskCommandParserTest {
    private SortTaskCommandParser parser = new SortTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        for (String comparingVar : TaskComparator.getAcceptedVar()) {
            assertParseSuccess(parser, comparingVar, new SortTaskCommand(comparingVar));
        }
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, ")($*!()",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskCommand.MESSAGE_USAGE));
    }
}
