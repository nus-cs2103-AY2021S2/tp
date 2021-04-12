package seedu.partyplanet.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_BIRTHDAY;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_BIRTHDAY_UPCOMING;
import static seedu.partyplanet.logic.commands.ListCommand.SORT_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_SORT;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.commands.ListCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

public class ListCommandParserTest {

    private static final ListCommandParser parser = new ListCommandParser();

    private static ListCommand parse(String userInput) throws Exception {
        ListCommand command;
        try {
            command = parser.parse(" " + userInput);
        } catch (ParseException e) {
            throw new Exception(e);
        }
        return command;
    }

    @Test
    public void parse_checkComparators() throws Exception {
        assertEquals(
                parse(PREFIX_SORT + " name " + PREFIX_ORDER + " desc").getComparator(),
                SORT_NAME.reversed()
        );
        assertEquals(
                parse(PREFIX_SORT + " birthday " + PREFIX_ORDER + " desc").getComparator(),
                SORT_BIRTHDAY.reversed()
        );
        assertEquals(
                parse(PREFIX_SORT + " upcoming " + PREFIX_ORDER + " desc").getComparator(),
                SORT_BIRTHDAY_UPCOMING
        );
    }
}
