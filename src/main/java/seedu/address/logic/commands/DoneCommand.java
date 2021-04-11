package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.identifier.Identifier;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * Done Command that changes an Event's EventStatus to Done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final Object MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the event identified by the identifier number on the event as Done\n"
            + "Parameters: IDENTIFIER (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_EVENT_SUCCESS = "Done Event: %1$s";

    public static final String MESSAGE_DONE_ALR_EVENT = "This event is already Done.";

    public final Identifier targetIdentifier;

    /**
     * @param identifier the event's Identifier
     */
    public DoneCommand(Identifier identifier) {
        requireNonNull(identifier);

        this.targetIdentifier = identifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getEventBook().getEventList().size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_INDEX_NO_EVENTS);
        }

        Event eventToMarkAsDone = model.getEventByIdentifier(targetIdentifier.getValue())
                .orElseThrow(() -> new CommandException(
                        String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER,
                                targetIdentifier.getValue())));

        boolean eventIsAlrDone = eventToMarkAsDone.getStatus() == EventStatus.DONE;

        if (eventIsAlrDone) {
            throw new CommandException(MESSAGE_DONE_ALR_EVENT);
        }

        Event doneEvent = doEvent(eventToMarkAsDone);

        model.setEvent(eventToMarkAsDone, doneEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_DONE_EVENT_SUCCESS, doneEvent));
    }

    private Event doEvent(Event eventToMarkAsDone) {
        EventName eventName = eventToMarkAsDone.getName();
        Description desc = eventToMarkAsDone.getDescription();
        EventPriority prio = eventToMarkAsDone.getPriority();
        Identifier eventIdentifier = Identifier.fromIdentifier(eventToMarkAsDone.getIdentifier());

        return new Event(eventName, EventStatus.DONE, prio, desc, eventIdentifier.getValue());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIdentifier.equals(((DoneCommand) other).targetIdentifier)); // state check
    }
}
