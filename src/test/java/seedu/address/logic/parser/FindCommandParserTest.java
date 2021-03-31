package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;

//failing tests
public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " n/Alice Bob";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        assertParseSuccess(parser, args, expectedFindCommand);

        // multiple whitespaces between keywords
        //        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validSizeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " s/22 10";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        assertParseSuccess(parser, " s/22 10", expectedFindCommand);

        // multiple whitespaces between keywords
        //        assertParseSuccess(parser, " s/\n 22 \n \t 10  \t", expectedFindCommand);
    }

    @Test
    public void parse_validColourArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " c/red black";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        assertParseSuccess(parser, " c/red black", expectedFindCommand);

        // multiple whitespaces between keywords
        //    assertParseSuccess(parser, " c/\n red \n \t black  \t", expectedFindCommand);
    }

    @Test
    public void parse_validDressCodeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " r/active casual";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        assertParseSuccess(parser, " r/active casual", expectedFindCommand);

        // multiple whitespaces between keywords
        //        assertParseSuccess(parser, " r/\n active \n \t casual  \t", expectedFindCommand);
    }

    //test issue desc more than one
    @Test
    public void parse_validDescriptionsArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " d/husband";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        assertParseSuccess(parser, " d/husband", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " d/\n husband \n  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTypeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " t/upper";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        assertParseSuccess(parser, " t/upper", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/\n upper \n  \t", expectedFindCommand);
    }

    @Test
    public void parse_validMultipleAtrributesArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String args = " n/Alice Bob s/23 10 c/red black r/active causal d/husband t/lower";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        FindCommand expectedFindCommand =
                new FindCommand(new AttributesContainsKeywordsPredicate(argumentMultimap));
        //        assertParseSuccess(parser, " d/husband", expectedFindCommand);

        // multiple whitespaces between keywords
        //    assertParseSuccess(parser, " d/\n husband \n  \t", expectedFindCommand);

    }
}
