package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortEventCommand;
import seedu.address.model.event.EventComparator;

public class SortEventCommandParserTest {
    private SortEventCommandParser parser = new SortEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        for (String comparingVar : EventComparator.getAcceptedVar()) {
            assertParseSuccess(parser, comparingVar, new SortEventCommand(comparingVar));
        }
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, ")($*!()",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE));
    }
}
