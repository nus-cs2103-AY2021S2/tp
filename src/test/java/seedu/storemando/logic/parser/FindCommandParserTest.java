package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.FindCommand;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_nonGenericFind_returnsFindCommand() {
        FindCommand expectedFindCommand =
            new FindCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList("Chocolate", "Tofu"),
                false));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Chocolate Tofu", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Chocolate \n \t Tofu  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_genericFind_returnsFindCommand() {
        FindCommand expectedFindCommand =
            new FindCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList("Chocolate", "Tofu"),
                true));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Chocolate Tofu", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Chocolate \n \t Tofu  \t", expectedFindCommand);
    }

}
