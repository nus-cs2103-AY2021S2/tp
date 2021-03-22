package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;

/**
 * Marks Event, identified using it's displayed index from PartyPlanet, as done.
 */
public class EDoneCommand extends Command {

    public static final String COMMAND_WORD = "edone";

    public static final String MESSAGE_EVENT_DONE_SUCCESS = "Events marked as completed: %s";
    public static final String MESSAGE_INVALID_EVENT_INDEX = "Invalid event indexes: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the event identified by the index number as done\n"
            + "Parameters: INDEX [INDEX]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    private final List<Index> targetIndexes;
    private final List<String> invalidIndexes;

    /**
     * Creates an EDoneCommand to mark done {@code Event} at specified indexes.
     */
    public EDoneCommand(List<Index> targetIndexes, List<String> invalidIndexes) {
        this.targetIndexes = targetIndexes;
        this.invalidIndexes = invalidIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        List<Event> doneEvents = new ArrayList<>();

        for (Index idx : targetIndexes) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                invalidIndexes.add("" + idx.getOneBased());
                continue;
            }
            Event event = lastShownList.get(idx.getZeroBased());

            if (event.isDone()) {
                invalidIndexes.add("" + idx.getOneBased());
                continue;
            }

            doneEvents.add(event);
        }

        for (Event e : doneEvents) {
            model.setEvent(e, e.setDone());
        }

        // If changes have been made
        if (!doneEvents.isEmpty()) {
            model.addState();
        }

        if (invalidIndexes.isEmpty()) {
            return new CommandResult(
                String.format(MESSAGE_EVENT_DONE_SUCCESS, displayEvents(doneEvents)));
        } else {
            return new CommandResult(
                String.format(MESSAGE_EVENT_DONE_SUCCESS + "\n" + MESSAGE_INVALID_EVENT_INDEX,
                        displayEvents(doneEvents),
                        String.join(", ", invalidIndexes)));
        }
    }

    /**
     * Returns list of events in the form "a, b, c,..."
     */
    private String displayEvents(List<Event> events) {
        return events.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EDoneCommand // instanceof handles nulls
                && targetIndexes.equals(((EDoneCommand) other).targetIndexes) // state check
                && invalidIndexes.equals(((EDoneCommand) other).invalidIndexes)); // state check
    }
}
