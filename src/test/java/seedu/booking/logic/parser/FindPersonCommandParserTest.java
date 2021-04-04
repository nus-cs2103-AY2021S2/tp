package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.FindPersonCommand;
import seedu.booking.model.person.NameContainsKeywordsPredicate;
import seedu.booking.model.person.Person;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPersonCommand() {
        List<Predicate<Person>> predicateList = new ArrayList<>();
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Pauline"));
        predicateList.add(namePredicate);

        FindPersonCommand expectedFindPersonCommand = new FindPersonCommand(predicateList);
        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/Alice Pauline", expectedFindPersonCommand);

        // multiple whitespaces in front and behind of keywords
        assertParseSuccess(parser, " n/\n Alice Pauline  \t", expectedFindPersonCommand);
    }

}

