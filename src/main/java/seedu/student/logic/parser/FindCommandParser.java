package seedu.student.logic.parser;

import seedu.student.logic.commands.FindCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MatriculationNumberContainsKeywordsPredicate;

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
