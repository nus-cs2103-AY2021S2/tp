package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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
                PREFIX_PHONE + " " + PREFIX_EMAIL + " " + PREFIX_ADDRESS + " " + PREFIX_TAG + " "
                        + PREFIX_REMARK;
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(arguments, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_REMARK);
        FilterCommand expectedFilterCommand = new FilterCommand(
                new DisplayFilterPredicate(argumentMultimap));
        assertParseSuccess(parser, arguments, expectedFilterCommand);
    }

    @Test
    public void parse_someFields_success() {
        String arguments = PREFIX_ADDRESS + " " + PREFIX_TAG + " " + PREFIX_REMARK;
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(arguments, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_REMARK);
        FilterCommand expectedFilterCommand = new FilterCommand(
                new DisplayFilterPredicate(argumentMultimap));
        assertParseSuccess(parser, arguments, expectedFilterCommand);
    }
}
