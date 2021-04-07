package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PinTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class PinTaskCommandParser implements Parser<PinTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PinTaskCommand
     * and returns a PinTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PinTaskCommand parse(String args) throws ParseException {
        try {
            Index index = SocheduleParserUtil.parseIndex(args);
            return new PinTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage()
                            + "%1$s", PinTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
