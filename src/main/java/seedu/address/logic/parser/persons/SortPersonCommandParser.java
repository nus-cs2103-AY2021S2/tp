package seedu.address.logic.parser.persons;

import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;

public class SortPersonCommandParser implements Parser<SortPersonCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SortPersonCommand parse(String userInput) throws ParseException {
        return new SortPersonCommand(PersonSortOption.NAME, PersonSortDirection.DESC);
    }



}
