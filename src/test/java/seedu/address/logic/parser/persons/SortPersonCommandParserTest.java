package seedu.address.logic.parser.persons;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.persons.FindPersonCommand;
import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;

class SortPersonCommandParserTest {

    private SortPersonCommandParser parser = new SortPersonCommandParser();

    @Test
    void parseEmpty() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs() {
        // no leading and trailing whitespaces
        SortPersonCommand expectedFindPersonCommand =
                new SortPersonCommand(PersonSortOption.NAME, PersonSortDirection.ASC);
        assertParseSuccess(parser, " "+PREFIX_SORT_BY+"name "+ PREFIX_SORT_DIRECTION+"asc",
                expectedFindPersonCommand);
    }
}
