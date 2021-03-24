package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStatus;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    //Event
    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the event book.";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param identifier of the person in the filtered person list to edit
     * @param editEventDescriptor details to edit the person with
     */
    public EditCommand(Index identifier, EditEventDescriptor editEventDescriptor) {
        requireNonNull(identifier);
        requireNonNull(editEventDescriptor);

        this.index = identifier;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getEventBook().getEventList().size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_INDEX_NO_EVENTS);
        }

        Optional<Event> optEventToEdit = model.getEventByIdentifier(index.getOneBased());
        Optional<Event> optEditedEvent = optEventToEdit
                .map(event -> createEditedEvent(event, editEventDescriptor));

        boolean modelHasNewEvent = optEditedEvent.map(model::hasEvent).orElse(false);
        boolean editedEventSameAsBefore = optEventToEdit
                .map(event -> event.isSameEvent(createEditedEvent(event, editEventDescriptor))).orElse(false);

        if (!editedEventSameAsBefore && modelHasNewEvent) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        Event eventToEdit = optEventToEdit
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER));
        Event editedEvent = optEditedEvent
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER));

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        EventName updatedName = editEventDescriptor.getEventName().orElse(eventToEdit.getName());
        EventStatus updatedEventStatus = editEventDescriptor.getEventStatus().orElse(eventToEdit.getStatus());
        Description updatedDescription = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        // commented out for v1.2
        // Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(eventToEdit.getTags());
        // Set<Person> updatedPersons = editPersonDescriptor.getPersons().orElse(eventToEdit.getPersons());

        return new Event(updatedName, updatedEventStatus, updatedDescription, eventToEdit.getIdentifier());
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
        return index.equals(e.index)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditEventDescriptor {
        private EventName eventName;
        private EventStatus eventStatus;
        private Description description;
        // private EventTime timeStart; // commented out for v1.2
        // private EventTime timeEnd; // commented out for v1.2
        // private Set<Tag> tags; // commented out for v1.2
        // private Set<Person> persons; // commented out for v1.2

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setDescription(toCopy.description);
            setEventStatus(toCopy.eventStatus);
            // setTags(toCopy.tags); // commented out for v1.2
            // setPersons(toCopy.persons); // commented out for v1.2
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, description, eventStatus);
        }

        public void setEventName(EventName eventName) {
            this.eventName = eventName;
        }

        public Optional<EventName> getEventName() {
            return Optional.ofNullable(eventName);
        }

        /*
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
        */
        public void setEventStatus(EventStatus eventStatus) {
            this.eventStatus = eventStatus;
        }
        public Optional<EventStatus> getEventStatus() {
            return Optional.ofNullable(eventStatus);
        }
        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        //        // Persons
        //        /**
        //         * Sets {@code tags} to this object's {@code tags}.
        //         * A defensive copy of {@code tags} is used internally.
        //         */
        //        public void setPersons(Set<Person> persons) {
        //            this.persons = (persons != null) ? new HashSet<>(persons) : null;
        //        }
        //
        //        /**
        //         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
        //         * if modification is attempted.
        //         * Returns {@code Optional#empty()} if {@code tags} is null.
        //         */
        //        public Optional<Set<Person>> getPersons() {
        //            return (persons != null) ? Optional.of(Collections.unmodifiableSet(persons)) : Optional.empty();
        //        }
        //
        //        /**
        //         * Sets {@code tags} to this object's {@code tags}.
        //         * A defensive copy of {@code tags} is used internally.
        //         */
        //        public void setTags(Set<Tag> tags) {
        //            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        //        }
        //
        //        /**
        //         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
        //         * if modification is attempted.
        //         * Returns {@code Optional#empty()} if {@code tags} is null.
        //         */
        //        public Optional<Set<Tag>> getTags() {
        //            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        //        }
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
                    && getEventStatus().equals(e.getEventStatus());
                    //&& getPersons().equals(e.getPersons())
                    //&& getTags().equals(e.getTags());
        }
    }
}
