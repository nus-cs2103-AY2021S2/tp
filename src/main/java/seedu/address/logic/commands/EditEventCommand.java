package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Event;
import seedu.address.model.event.Time;

/**
 * Edits the details of an existing and uncompleted event in SOChedule.
 */
public class EditEventCommand extends Command {
    public static final String COMMAND_WORD = "edit_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the uncompleted event identified "
            + "by the index number used in the displayed event list."
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_STARTDATE + "STARTDATE] "
            + "[" + PREFIX_STARTTIME + "STARTTIME] "
            + "[" + PREFIX_ENDDATE + "ENDDATE] "
            + "[" + PREFIX_ENDTIME + "ENDTIME] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "editedEventName "
            + PREFIX_ENDDATE + "2023-01-07 "
            + PREFIX_TAG + "MA3110";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the SOChedule.";
    public static final String MESSAGE_EXPIRED_EVENT = "An expired event cannot be edited.";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index               of the event in the filtered event list to edit.
     * @param editEventDescriptor details to edit the event with.
     */
    public EditEventCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);

        this.index = index;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = lastShownList.get(index.getZeroBased());

        /*if (eventToEdit.isComplete()) {
            throw new CommandException(MESSAGE_EXPIRED_EVENT);
        }*/

        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

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

        Name updatedName = editEventDescriptor.getName().orElse(eventToEdit.getName());
        Date updatedStartDate = editEventDescriptor.getStartDate().orElse(eventToEdit.getStartDate());
        Time updatedStartTime = editEventDescriptor.getStartTime().orElse(eventToEdit.getStartTime());
        Date updatedEndDate = editEventDescriptor.getEndDate().orElse(eventToEdit.getEndDate());
        Time updatedEndTime = editEventDescriptor.getEndTime().orElse(eventToEdit.getEndTime());
        Set<Category> updatedCategories = editEventDescriptor.getCategories().orElse(eventToEdit.getCategories());
        Set<Tag> updatedTags = editEventDescriptor.getTags().orElse(eventToEdit.getTags());

        return new Event(updatedName, updatedStartDate, updatedStartTime, updatedEndDate,
                updatedEndTime, updatedCategories, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        // state check
        EditEventCommand e = (EditEventCommand) other;
        return index.equals(e.index)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    public static class EditEventDescriptor {
        private Name name;
        private Date startDate;
        private Time startTime;
        private Date endDate;
        private Time endTime;
        private Set<Category> categories;
        private Set<Tag> tags;

        public EditEventDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code categories} are used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setName(toCopy.name);
            setStartDate(toCopy.startDate);
            setStartTime(toCopy.startTime);
            setEndDate(toCopy.endDate);
            setEndTime(toCopy.endTime);
            setCategories(toCopy.categories);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, startDate, startTime, endDate, endTime, categories, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Optional<Date> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartTime(Time startTime) {
            this.startTime = startTime;
        }

        public Optional<Time> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public Optional<Date> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public void setEndTime(Time endTime) {
            this.endTime = endTime;
        }

        public Optional<Time> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code categories} to this object's {@code categories}.
         * A defensive copy of {@code categories} is used internally.
         */
        public void setCategories(Set<Category> categories) {
            this.categories = (categories != null) ? new HashSet<>(categories) : null;
        }

        /**
         * Returns an unmodifiable category set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code categories} is null.
         */
        public Optional<Set<Category>> getCategories() {
            return (categories != null) ? Optional.of(Collections.unmodifiableSet(categories)) : Optional.empty();
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

            return getName().equals(e.getName())
                    && getStartDate().equals(e.getStartDate())
                    && getStartTime().equals(e.getStartTime())
                    && getEndDate().equals(e.getEndDate())
                    && getEndTime().equals(e.getEndTime())
                    && getCategories().equals(e.getCategories())
                    && getTags().equals(e.getTags());
        }
    }
}

