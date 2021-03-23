package seedu.address.logic.parser;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MatriculationNumberContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        logger.info("----------------[MATRIC NUMBER TO BE SEARCHED:][" + args.trim() + "]");

        ParserUtil parserUtil = new ParserUtil();
        MatriculationNumber matriculationNumber = parserUtil.parseMatric(args.trim());

        assert matriculationNumber.toString().equals(matriculationNumber.toString().toUpperCase());

        return new FindCommand(new MatriculationNumberContainsKeywordsPredicate(matriculationNumber.toString()));
    }
}
