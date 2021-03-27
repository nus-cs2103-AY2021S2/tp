package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.logic.commands.FindCommand;
import seedu.cakecollate.model.order.ContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        HashMap<Prefix, List<String>> map = new HashMap<>();
        map.put(PREFIX_NAME, Arrays.asList("Alice", "Bob"));
        FindCommand expectedFindCommand =
                new FindCommand(new ContainsKeywordsPredicate(map));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);

        // multiple prefixes
        map.put(PREFIX_PHONE, Arrays.asList("12345678"));
        map.put(PREFIX_ORDER_DESCRIPTION, Arrays.asList("Chocolate", "Cake"));
        expectedFindCommand = new FindCommand(new ContainsKeywordsPredicate(map));
        assertParseSuccess(parser, " n/Alice Bob p/12345678 o/Chocolate Cake", expectedFindCommand);

        // no prefixes
        map.clear();
        map.put(new Prefix("all/"), Arrays.asList("Alice", "Bob", "12345678", "Chocolate", "Cake"));
        expectedFindCommand = new FindCommand(new ContainsKeywordsPredicate(map));
        assertParseSuccess(parser, "Alice Bob 12345678 Chocolate Cake", expectedFindCommand);
    }

}
