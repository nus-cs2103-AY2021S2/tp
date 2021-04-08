package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.identifier.Identifier;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * Edits the details of an existing event in Focuris.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the identifier used in the displayed event list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: IDENTIFIER (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] " + " "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]" + " "
            + "[" + PREFIX_PRIORITY + "PRIORITY]" + " "
            + "[" + PREFIX_STATUS + "STATUS]" + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Finish CS2030S Lab 1 "
            + PREFIX_PRIORITY + "high";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the event book.";

    private final Identifier identifier;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param identifier of the event in the filtered event list to edit
     * @param editEventDescriptor details to edit the event with
     */
    public EditCommand(Identifier identifier, EditEventDescriptor editEventDescriptor) {
        requireNonNull(identifier);
        requireNonNull(editEventDescriptor);

        this.identifier = identifier;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // if no events
        if (model.getEventBook().getEventList().size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_INDEX_NO_EVENTS);
        }

        // check if identifier is too big or negative

        Optional<Event> optEventToEdit = model.getEventByIdentifier(identifier.getValue());
        Optional<Event> optEditedEvent = optEventToEdit
                .map(event -> createEditedEvent(event, editEventDescriptor));

        boolean modelHasNewEvent = optEditedEvent.map(model::hasEvent).orElse(false);
        boolean editedEventSameAsBefore = optEventToEdit
                .map(event -> event.isSameEvent(createEditedEvent(event, editEventDescriptor))).orElse(false);

        if (!editedEventSameAsBefore && modelHasNewEvent) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        Event eventToEdit = optEventToEdit
                .orElseThrow(() -> new CommandException(
                        String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER,
                                identifier.getValue())));
        Event editedEvent = optEditedEvent
                .orElseThrow(() -> new CommandException(
                        String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER,
                                identifier.getValue())));

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        EventName updatedName = editEventDescriptor.getEventName().orElse(eventToEdit.getName());
        EventStatus updatedEventStatus = editEventDescriptor.getEventStatus().orElse(eventToEdit.getStatus());
        Description updatedDescription = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        EventPriority updatedEventPriority = editEventDescriptor.getEventPriority().orElse(eventToEdit.getPriority());

        return new Event(updatedName, updatedEventStatus, updatedEventPriority,
                updatedDescription, eventToEdit.getIdentifier());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return identifier.equals(e.identifier)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the even t with. Each non-empty field value will replace the
     * corresponding field value of the event.
     */
    public static class EditEventDescriptor {
        private EventName eventName;
        private EventStatus eventStatus;
        private EventPriority eventPriority;
        private Description description;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setDescription(toCopy.description);
            setEventStatus(toCopy.eventStatus);
            setEventPriority(toCopy.eventPriority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, description, eventStatus, eventPriority);
        }

        public void setEventName(EventName eventName) {
            this.eventName = eventName;
        }

        public Optional<EventName> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public void setEventStatus(EventStatus eventStatus) {
            this.eventStatus = eventStatus;
        }
        public Optional<EventStatus> getEventStatus() {
            return Optional.ofNullable(eventStatus);
        }

        public void setEventPriority(EventPriority eventPriority) {
            this.eventPriority = eventPriority;
        }
        public Optional<EventPriority> getEventPriority() {
            return Optional.ofNullable(eventPriority);
        }

        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEventDescriptor)) {
                return false;
            }

            // state check
            EditEventDescriptor e = (EditEventDescriptor) other;

            return getEventName().equals(e.getEventName())
                    && getDescription().equals(e.getDescription())
                    && getEventStatus().equals(e.getEventStatus())
                    && getEventPriority().equals(e.getEventPriority());
        }
    }
}
