package seedu.storemando.logic.parser;

import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        ListCommand expectedListCommand = new ListCommand();
        assertParseSuccess(parser, "     ", expectedListCommand);
    }

    @Test
    public void parse_validArgs_locationListReturnsListCommand() {
        ListCommand expectedListCommand =
            new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList("Living", "Room")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Living Room", expectedListCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "l/ \n Living \n \t Room  \t", expectedListCommand);
    }

    @Test
    public void parse_validArgs_genericListReturnsListCommand() {
        ListCommand expectedListCommand =
            new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList("Living", "Room")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Living Room", expectedListCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Living \n \t Room  \t", expectedListCommand);
    }

}
