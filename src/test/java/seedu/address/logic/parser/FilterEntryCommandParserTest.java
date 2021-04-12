package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterEntryCommand;
import seedu.address.model.entry.EntryTagsContainKeywordsPredicate;

/**
 * Contains tests to make sure the parser instantiates the correct {@code FilterEntryCommand}.
 */
public class FilterEntryCommandParserTest {

    private final FilterEntryCommandParser parser = new FilterEntryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEntryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterEntryCommand() {
        // no leading and trailing whitespaces
        FilterEntryCommand expectedCommand =
                new FilterEntryCommand(new EntryTagsContainKeywordsPredicate(Arrays.asList("Math", "History")));
        assertParseSuccess(parser, "Math History", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Math \n \t History  \t", expectedCommand);
    }
}
