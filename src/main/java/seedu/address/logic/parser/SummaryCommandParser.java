package seedu.address.logic.parser;

import seedu.address.logic.commands.SummaryCommand;

public class SummaryCommandParser implements Parser<SummaryCommand> {
    /**
     * Returns a SummaryCommand object for execution.
     */
    public SummaryCommand parse(String args) {
        return new SummaryCommand();
    }
}
