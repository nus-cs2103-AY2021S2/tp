package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearCommand;

public class ClearCommandParser implements Parser<ClearCommand> {
    /**
     * Returns a ClearCommand object for execution.
     */
    public ClearCommand parse(String args) {
        return new ClearCommand();
    }
}
