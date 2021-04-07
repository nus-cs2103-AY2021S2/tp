package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommand.FindEventCommand;
import seedu.address.model.event.DescriptionContainsKeywordsPredicate;

public class FindEventCommandParserTest {

    private final FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "        ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindEventCommand() {
        // no leading and trailing whitespaces
        FindEventCommand expectedFindEventCommand =
                new FindEventCommand(new DescriptionContainsKeywordsPredicate(
                        Arrays.asList("Meeting", "Appointment")));
        assertParseSuccess(parser, " g/Meeting Appointment", expectedFindEventCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " g/\n Meeting \n \t Appointment  \t", expectedFindEventCommand);
    }
}
