package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.commands.ListCommand.ASC;
import static seedu.partyplanet.logic.commands.ListCommand.DESC;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.Comparator;
import java.util.Optional;

import seedu.partyplanet.logic.commands.ListCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.person.Person;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_SORT);

        Optional<String> sortType = argumentMultimap.getValue(PREFIX_SORT);

        if (sortType.isEmpty()) {
            return new ListCommand(ASC);
        }

        Comparator<Person> comparator;
        switch (sortType.get()) {
        case "asc":
            comparator = ASC;
            break;
        case "desc":
            comparator = DESC;
            break;
        default:
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        return new ListCommand(comparator);
    }

}
