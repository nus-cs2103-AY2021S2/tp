package seedu.ta.logic.parser;

import static seedu.ta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ta.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ta.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.ta.logic.commands.FindEntryCommand;
import seedu.ta.model.entry.EntryNameContainsKeywordsPredicate;

/**
 * Contains tests to make sure the parser instantiates the correct {@code FindEntryCommand}.
 */
public class FindEntryCommandParserTest {

    private final FindEntryCommandParser parser = new FindEntryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEntryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindEntryCommand() {
        // no leading and trailing whitespaces
        FindEntryCommand expectedCommand =
                new FindEntryCommand(new EntryNameContainsKeywordsPredicate(Arrays.asList("Party", "Consultation")));
        assertParseSuccess(parser, "Party Consultation", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Party \n \t Consultation  \t", expectedCommand);
    }
}
