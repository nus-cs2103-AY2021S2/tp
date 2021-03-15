package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MatriculationNumberContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        ParserUtil parserUtil = new ParserUtil();
        MatriculationNumber matriculationNumber = parserUtil.parseMatric(args.trim());

        return new FindCommand(new MatriculationNumberContainsKeywordsPredicate(matriculationNumber.toString()));
    }
}
