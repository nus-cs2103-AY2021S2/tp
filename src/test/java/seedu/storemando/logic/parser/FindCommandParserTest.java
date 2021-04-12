package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_PARTIAL_NAME;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.FindCommand;
import seedu.storemando.model.item.predicate.ItemNameContainsKeywordsPredicate;
import seedu.storemando.model.item.predicate.ItemNameContainsPartialKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_nonGenericFindReturnsFindCommand() {
        FindCommand expectedFindCommand =
            new FindCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList("Chocolate", "Tofu")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Chocolate Tofu", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Chocolate \n \t Tofu  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_genericFindReturnsFindCommand() {
        FindCommand expectedFindCommand =
            new FindCommand(new ItemNameContainsPartialKeywordsPredicate(Collections.singletonList("Chocolate")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " " + PREFIX_PARTIAL_NAME + "Chocolate", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n " + PREFIX_PARTIAL_NAME + "Chocolate \n", expectedFindCommand);
    }

    @Test
    // String found before prefix
    public void parse_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, "nonempty " + PREFIX_PARTIAL_NAME + "choco",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
