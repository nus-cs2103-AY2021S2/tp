package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

/**
 * Deletes all events currently displayed.
 */
public class EDeleteClearCommand extends EDeleteCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = new ArrayList<>(model.getFilteredEventList());
        List<Event> deletedEvents = new ArrayList<>();

        for (Event e : lastShownList) {
            deletedEvents.add(e);
            model.deleteEvent(e);
        }

        // Only save state if there are changes (event deleted)
        if (!deletedEvents.isEmpty()) {
            model.addState();
        }
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, displayEvents(deletedEvents)));
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> deletedEvents) {
        return deletedEvents.stream()
            .map(e -> e.getName().toString())
            .reduce((a, b) -> a + ", " + b)
            .orElse("None!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof EDeleteClearCommand); // instanceof handles nulls
    }
}

