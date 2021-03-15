package seedu.address.logic.parser;


import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        if (args.equals(" persons") || args.equals(" sessions")) {
            return new ListCommand(args.trim());
        } else {
            throw new ParseException("List description should either be persons or sessions!");
        }
    }
}
