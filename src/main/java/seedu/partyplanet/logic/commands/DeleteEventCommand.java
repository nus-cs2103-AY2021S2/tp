package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

/**
 * Deletes an event or events identified using it's displayed index from PartyPlanet.
 */
public class DeleteEventCommand extends EDeleteCommand {

    private final List<Index> targetIndexes;

    /**
     * Creates an DeleteEventCommand to delete the {@code Event} at specified indexes.
     */
    public DeleteEventCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        List<Event> deletedEvents = new ArrayList<>();

        for (Index idx : targetIndexes) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            }

            assert idx.getZeroBased() >= 0;
            assert index.getZeroBased() < lastShownList.size();
            Event eventToDelete = lastShownList.get(idx.getZeroBased());
            deletedEvents.add(eventToDelete);
        }

        for (Event eventToDelete : deletedEvents) {
            model.deleteEvent(eventToDelete);
        }
        model.addState();
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, displayEvents(deletedEvents)));
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> deletedEvents) {
        return deletedEvents.stream()
            .map(e -> e.getName().toString())
            .reduce((a, b) -> a + ", " + b)
            .get();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteEventCommand // instanceof handles nulls
            && targetIndexes.equals(((DeleteEventCommand) other).targetIndexes)); // state check
    }
}

