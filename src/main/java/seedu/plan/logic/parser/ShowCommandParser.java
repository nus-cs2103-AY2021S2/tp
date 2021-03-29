package seedu.plan.logic.parser;

import seedu.plan.commons.core.Messages;
import seedu.plan.logic.commands.ShowCapCommand;
import seedu.plan.logic.commands.ShowCommand;
import seedu.plan.logic.commands.ShowMcCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    private final String mcs = "mcs";
    private final String cap = "cap";

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        String strippedArgs = args.strip();
        ShowCommand showCommand;

        if (strippedArgs.equals(mcs)) {
            showCommand = new ShowMcCommand();
        } else if (strippedArgs.equals(cap)) {
            showCommand = new ShowCapCommand();
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        return showCommand;
    }
}
