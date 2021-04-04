package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid since only one valid argument (done, not done) is allowed
        assertParseFailure(parser, "done not done",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.INVALID_INPUT));

        assertParseFailure(parser, "-1 done!@",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.INVALID_INPUT));
    }

}
