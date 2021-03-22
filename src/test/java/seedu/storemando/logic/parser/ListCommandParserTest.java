package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;
import seedu.storemando.model.tag.TagContainsKeywordsPredicate;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        ListCommand expectedListCommand = new ListCommand();
        assertParseSuccess(parser, "     ", expectedListCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "chocolate",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validLocationArgs_returnsListCommand() {
        String[] locationKeyword = {"kitchen"};
        ListCommand expectedListCommand =
            new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList(locationKeyword)));
        assertParseSuccess(parser, " l/kitchen", expectedListCommand);
    }

    @Test
    public void parse_validTagArgs_returnsListCommand() {
        String[] locationKeyword = {"favourite"};
        ListCommand expectedListCommand =
            new ListCommand(new TagContainsKeywordsPredicate(Arrays.asList(locationKeyword)));
        assertParseSuccess(parser, " t/favourite", expectedListCommand);
    }

    @Test
    public void parse_invalidArgsWithTagAndLocation_throwsParseException() {
        assertParseFailure(parser, " l/kitchen t/favourite",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
