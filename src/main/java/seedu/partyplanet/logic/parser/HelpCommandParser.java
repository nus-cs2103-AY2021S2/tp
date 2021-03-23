package seedu.partyplanet.logic.parser;

import seedu.partyplanet.logic.commands.HelpCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new HelpCommand();
        } else {
            String[] commandWords = trimmedArgs.split("\\s+");
            return new HelpCommand(commandWords[0]);
        }
    }
}
