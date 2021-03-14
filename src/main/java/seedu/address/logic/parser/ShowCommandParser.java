package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowCAPCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.ShowMCCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    private final String MCS = "mcs";
    private final String CAP = "cap";

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        ShowCommand showCommand;

        switch (args) {
        case MCS:
            showCommand = new ShowMCCommand();
            break;
        case CAP:
            showCommand = new ShowCAPCommand();
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        return showCommand;
    }
}
