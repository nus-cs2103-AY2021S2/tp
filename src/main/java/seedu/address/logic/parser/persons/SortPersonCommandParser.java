package seedu.address.logic.parser.persons;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;

public class SortPersonCommandParser implements Parser<SortPersonCommand> {

    /**
     * Parses {@code args} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SortPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_BY, PREFIX_SORT_DIRECTION);

        try {
            PersonSortOption option = PersonSortOption.valueOf(argMultimap.getValue(PREFIX_SORT_BY).get());
            PersonSortDirection direction = PersonSortDirection.valueOf(argMultimap.getValue(PREFIX_SORT_DIRECTION).
                    get());
            return new SortPersonCommand(option, direction);
        } catch (Exception e) {
            throw new ParseException(SortPersonCommand.MESSAGE_USAGE);
        }

    }



}
