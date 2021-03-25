package dog.pawbook.logic.parser;

import dog.pawbook.logic.commands.Command;
import dog.pawbook.logic.parser.exceptions.ParseException;

public abstract class CommandWithPrefixParser<T extends Command> implements Parser<T> {
    /**
     * Returns an array containing all accepted prefixes.
     */
    protected abstract Prefix[] getAllPrefixes();

    /**
     * Returns the help message for the add command.
     */
    protected abstract String getUsageText();

    /**
     * Extracts all argument values tagged by the corresponding prefixes.
     * @throws ParseException if the user input does not conform the expected format
     */
    protected ArgumentMultimap extractArguments(String args) throws ParseException {
        return ArgumentTokenizer.tokenize(args, getAllPrefixes());
    }
}
