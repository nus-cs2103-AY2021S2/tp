package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAB;

import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the details panel to display the given tab.\n"
            + "Parameters: TAB (upcoming events | streaks)\n"
            + "Example: " + COMMAND_WORD + " upcoming events";

    public static final String MESSAGE_DETAILS_SUCCESS = "Displaying the %1$s tab on the right panel";

    private final DetailsPanelTab tab;

    public ViewCommand(DetailsPanelTab tab) {
        this.tab = tab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // NOTE: replace with switch case if more such tabs are added
        if (tab == DetailsPanelTab.PERSON_DETAILS) {
            throw new CommandException(String.format(MESSAGE_INVALID_TAB, DetailsCommand.MESSAGE_USAGE));
        }

        return new CommandResult(String.format(MESSAGE_DETAILS_SUCCESS, tab), tab);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand e = (ViewCommand) other;
        return tab.equals(e.tab);
    }
}
