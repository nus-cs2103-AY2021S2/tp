package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB;

import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Returns true if the tab provided is accepted by the view command
     */
    private boolean canViewTab(DetailsPanelTab tab) {
        switch (tab) {
        case UPCOMING_EVENTS:
        case STREAKS:
            return true;
        default:
            return false;
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ViewCommand parse(String userInput) throws ParseException {
        try {
            DetailsPanelTab tab = DetailsPanelTab.fromString(userInput.trim());

            if (!canViewTab(tab)) {
                throw new ParseException(String.format(MESSAGE_INVALID_TAB, ViewCommand.MESSAGE_USAGE));
            }

            return new ViewCommand(tab);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_TAB, ViewCommand.MESSAGE_USAGE));
        }
    }
}
