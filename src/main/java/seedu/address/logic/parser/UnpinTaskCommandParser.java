package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnpinTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnpinTaskCommandParser implements Parser<UnpinTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnpinTaskCommand
     * and returns a UnpinTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnpinTaskCommand parse(String args) throws ParseException {
        try {
            Index index = SocheduleParserUtil.parseIndex(args);
            return new UnpinTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage()
                            + "%1$s", UnpinTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
