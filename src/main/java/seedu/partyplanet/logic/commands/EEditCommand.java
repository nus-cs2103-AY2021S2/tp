package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.Optional;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.commons.util.CollectionUtil;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.remark.Remark;

/**
 * Edits the details of an existing event in PartyPlanet.
 */
public class EEditCommand extends Command {

    public static final String COMMAND_WORD = "eedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed event list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_DATE + " DATE] "
            + "[" + PREFIX_REMARK + " REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + " 01 Apr 2019 "
            + PREFIX_REMARK + " Prank the april babies ;)";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD + " INDEX [-n NAME] [-d DATE] [-r REMARK]";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in PartyPlanet.";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index of the event in the filtered event list to edit.
     * @param editEventDescriptor details to edit the event with.
     */
    public EEditCommand(Index index, EditEventDescriptor editEventDescriptor) {
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

        assert index.getZeroBased() >= 0;
        assert index.getZeroBased() < lastShownList.size();
        Event eventToEdit = lastShownList.get(index.getZeroBased());
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        model.addState(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    /**
     * Creates and returns an {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        Name updatedName = editEventDescriptor.getName().orElse(eventToEdit.getName());
        EventDate updatedDate = editEventDescriptor.getDate().orElse(eventToEdit.getEventDate());
        Remark updatedRemark = editEventDescriptor.getRemark().orElse(eventToEdit.getRemark());
        boolean isDone = eventToEdit.isDone();

        return new Event(updatedName, updatedDate, updatedRemark, isDone);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EEditCommand)) {
            return false;
        }

        // state check
        EEditCommand e = (EEditCommand) other;
        return index.equals(e.index)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the event with. Each non-empty field value will replace the
     * corresponding field value of the event.
     */
    public static class EditEventDescriptor {
        private Name name;
        private EventDate date;
        private Remark remark;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setName(toCopy.name);
            setDate(toCopy.date);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, date, remark);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }


        public Optional<EventDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(EventDate date) {
            this.date = date;
        }


        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
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
                    && getDate().equals(e.getDate())
                    && getRemark().equals(e.getRemark());
        }
    }
}
