package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.DisplayFilterPredicate;

class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        FilterCommand expectedFilterCommand = new FilterCommand(new DisplayFilterPredicate());
        assertParseSuccess(parser, "", expectedFilterCommand);
    }

    @Test
    public void parse_allFields_success() {
        String arguments =
                PREFIX_PHONE + " " + PREFIX_EMAIL + " " + PREFIX_ADDRESS + " " + PREFIX_TAG;
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(arguments, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        FilterCommand expectedFilterCommand = new FilterCommand(
                new DisplayFilterPredicate(argumentMultimap));
        assertParseSuccess(parser, arguments, expectedFilterCommand);
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // there are no filter commands that cannot be aliased - parser always return true
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        // there are no filter commands that cannot be aliased - parser always return true
    }
}
