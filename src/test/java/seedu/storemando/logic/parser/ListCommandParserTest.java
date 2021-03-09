package seedu.storemando.logic.parser;

import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        // no leading and trailing whitespaces
        ListCommand expectedListCommand =
            new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedListCommand);
    }

}
