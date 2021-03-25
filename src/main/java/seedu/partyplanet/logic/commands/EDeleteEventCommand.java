package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

/**
 * Deletes an event or events identified using it's displayed index from PartyPlanet.
 */
public class EDeleteEventCommand extends EDeleteCommand {

    private final List<Index> targetIndexes;
    private final List<String> invalidIndexes;

    /**
     * Creates an EDeleteEventCommand to delete the {@code Event} at specified indexes.
     */
    public EDeleteEventCommand(List<Index> targetIndexes, List<String> invalidIndexes) {
        this.targetIndexes = targetIndexes;
        this.invalidIndexes = invalidIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        List<Event> deletedEvents = new ArrayList<>();

        for (Index idx : targetIndexes) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                invalidIndexes.add("" + idx.getOneBased());
                continue;
            }

            assert idx.getZeroBased() >= 0;
            assert idx.getZeroBased() < lastShownList.size();
            Event eventToDelete = lastShownList.get(idx.getZeroBased());
            deletedEvents.add(eventToDelete);
        }

        for (Event eventToDelete : deletedEvents) {
            model.deleteEvent(eventToDelete);
        }

        // If changes have been made
        if (!deletedEvents.isEmpty()) {
            model.addState();
        }

        if (invalidIndexes.isEmpty()) {
            return new CommandResult(
                String.format(MESSAGE_DELETE_EVENT_SUCCESS, displayEvents(deletedEvents)));
        } else {
            return new CommandResult(
                String.format(MESSAGE_DELETE_EVENT_SUCCESS + "\n" + MESSAGE_INVALID_EVENT_INDEX,
                        displayEvents(deletedEvents),
                        String.join(", ", invalidIndexes)));
        }
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> deletedEvents) {
        return deletedEvents.stream()
            .map(e -> e.getName().toString())
            .reduce((a, b) -> a + ", " + b)
            .orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof EDeleteEventCommand // instanceof handles nulls
            && targetIndexes.equals(((EDeleteEventCommand) other).targetIndexes)); // state check
    }
}

