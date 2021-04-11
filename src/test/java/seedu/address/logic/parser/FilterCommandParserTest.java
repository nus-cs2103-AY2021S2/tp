package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.logic.parser.FilterCommandParser.FORCE_PREAMBLE;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.DisplayFilterPredicate;

class FilterCommandParserTest {

    private static final String INVALID_PREFIX = "-dfdf";

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        FilterCommand expectedFilterCommand = new FilterCommand(new DisplayFilterPredicate());
        assertParseSuccess(parser, "", expectedFilterCommand);
    }

    @Test
    public void parse_allFields_success() {
        String arguments = FORCE_PREAMBLE + PREFIX_PHONE + " " + PREFIX_EMAIL + " " + PREFIX_COMPANY
                + " " + PREFIX_JOB_TITLE + " " + PREFIX_ADDRESS + " " + PREFIX_TAG + " "
                + PREFIX_REMARK;
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(arguments, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_COMPANY, PREFIX_JOB_TITLE, PREFIX_TAG, PREFIX_REMARK);
        FilterCommand expectedFilterCommand = new FilterCommand(
                new DisplayFilterPredicate(argumentMultimap),
                Arrays.asList(PREFIX_PHONE.getPrefix(), PREFIX_EMAIL.getPrefix(),
                        PREFIX_COMPANY.getPrefix(), PREFIX_JOB_TITLE.getPrefix(),
                        PREFIX_ADDRESS.getPrefix(), PREFIX_TAG.getPrefix(),
                        PREFIX_REMARK.getPrefix())
        );
        assertParseSuccess(parser, arguments, expectedFilterCommand);
    }

    @Test
    public void parse_someFields_success() {
        String arguments = FORCE_PREAMBLE + PREFIX_ADDRESS + " " + PREFIX_TAG + " " + PREFIX_REMARK;
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(" " + arguments, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_COMPANY, PREFIX_JOB_TITLE, PREFIX_TAG, PREFIX_REMARK);
        FilterCommand expectedFilterCommand = new FilterCommand(
                new DisplayFilterPredicate(argumentMultimap),
                Arrays.asList(PREFIX_ADDRESS.getPrefix(), PREFIX_TAG.getPrefix(),
                        PREFIX_REMARK.getPrefix()));
        assertParseSuccess(parser, arguments, expectedFilterCommand);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        // invalid prefix
        assertParseFailure(parser, FORCE_PREAMBLE + INVALID_PREFIX,
                String.format(MESSAGE_INVALID_PREFIX, INVALID_PREFIX) + "\n"
                        + FilterCommand.MESSAGE_USAGE);

        // invalid prefix
        assertParseFailure(parser, FORCE_PREAMBLE + INVALID_PREFIX + " " + INVALID_PREFIX,
                String.format(MESSAGE_INVALID_PREFIX, INVALID_PREFIX + " " + INVALID_PREFIX) + "\n"
                        + FilterCommand.MESSAGE_USAGE);

        // invalid prefix followed by valid
        assertParseFailure(parser, FORCE_PREAMBLE + INVALID_PREFIX + " " + PREFIX_PHONE,
                String.format(MESSAGE_INVALID_PREFIX, INVALID_PREFIX) + "\n"
                        + FilterCommand.MESSAGE_USAGE);

        // valid followed by invalid prefix
        assertParseFailure(parser, FORCE_PREAMBLE + PREFIX_PHONE + " " + INVALID_PREFIX,
                String.format(MESSAGE_INVALID_PREFIX, PREFIX_PHONE + INVALID_PREFIX) + "\n"
                        + FilterCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // there are no filter commands that cannot be aliased - parser always return true
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // valid prefix to alias
        assertValidCommandToAliasSuccess(parser, PREFIX_ADDRESS + " " + PREFIX_TAG);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        assertValidCommandToAliasFailure(parser, INVALID_PREFIX);

        assertValidCommandToAliasFailure(parser, INVALID_PREFIX + " " + INVALID_PREFIX);
    }
}
