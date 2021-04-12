package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

    @Test
    public void parse_validListUncompletedTasks_success() {
        String input = "not done";
        ListCommand expectedCommand = new ListCommand(false);
        try {
            assertEquals(new ListCommand(false), parser.parse(input));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_validListAllTasks_success() {
        String input = "";
        ListCommand expectedCommand = new ListCommand(true);
        try {
            assertEquals(new ListCommand(true), parser.parse(input));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertParseSuccess(parser, input, expectedCommand);
    }

}
