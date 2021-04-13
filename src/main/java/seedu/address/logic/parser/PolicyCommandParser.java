package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class PolicyCommandParser implements Parser<PolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PolicyCommand
     * and returns a PolicyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PolicyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PolicyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(pe.getLocalizedMessage() + "\n" + PolicyCommand.MESSAGE_USAGE, pe);
        }
    }

}
