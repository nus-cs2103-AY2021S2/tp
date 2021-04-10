package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static chim.logic.parser.CliSyntax.PREFIX_NAME;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import chim.logic.commands.FindCustomerCommand;
import chim.model.customer.Customer;
import chim.model.customer.predicates.CustomerNamePredicate;
import chim.model.util.predicate.CompositeFieldPredicate;

public class FindCustomerCommandParserTest {

    private final FindCustomerCommandParser parser = new FindCustomerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        final List<String> keywords = Arrays.asList("Alice", "Bob");

        // no leading and trailing whitespaces
        FindCustomerCommand expectedFindCustomerCommand =
                new FindCustomerCommand(new CompositeFieldPredicate<Customer>(new CustomerNamePredicate(keywords)));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + "Alice Bob", expectedFindCustomerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
            expectedFindCustomerCommand);
    }

}
