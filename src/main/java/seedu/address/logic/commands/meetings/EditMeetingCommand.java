package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing meeting in meet buddy.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list (and contact list"
            + " if you want to modify the contacts related). "
            + "Existing values will be overwritten by the input values, and at least one field must be indicated.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_START_TIME + "START TIME] "
            + "[" + PREFIX_END_TIME + "END TIME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "[" + PREFIX_GROUP + "GROUP]..."
            + "[" + PREFIX_PERSON_CONNECTION + "INDEX OF PERSON RELATED]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2103 Lecture "
            + PREFIX_START_TIME + "2021-03-12 14:00 "
            + PREFIX_END_TIME + "2021-03-12 16:00 "
            + PREFIX_DESCRIPTION + "Week 7 "
            + PREFIX_PRIORITY + "3 "
            + PREFIX_GROUP + "lectures "
            + PREFIX_GROUP + "SoC "
            + PREFIX_PERSON_CONNECTION + "1 "
            + PREFIX_PERSON_CONNECTION + "2";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the meeting book.";
    public static final String MESSAGE_CLASH_MEETING = "This meeting clashes with the following existing meetings \n%s";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;
    private final Set<Index> connectionToPerson = new HashSet<>();

    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }
    /**
     * Set the connection indices to persons.
     */
    public EditMeetingCommand setConnectionToPerson(Set<Index> indices) {
        this.connectionToPerson.addAll(indices);
        return this;
    }
    /**
     * Get the connection indices to persons.
     */
    public Set<Index> getConnectionToPerson() {
        return this.connectionToPerson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting;
        try {
            editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);
        } catch (InvalidMeetingException err) {
            throw new CommandException(err.getMessage());
        }


        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        if (model.clashesExceptOne(meetingToEdit, editedMeeting)) {
            List<Meeting> listOfClashingMeetings = model.getClashes(editedMeeting);
            String formatMeetingListString = CommandDisplayUtil.formatElementsIntoRows(listOfClashingMeetings);
            throw new CommandException(String.format(MESSAGE_CLASH_MEETING, formatMeetingListString));
        }

        // Reconstruct connection.
        Set<Person> existedPersonsConnection = meetingToEdit.getConnectionToPerson();
        model.deleteAllPersonMeetingConnectionByMeeting(meetingToEdit);
        // If the user does not try to modify the persons related, then preserve the old connection.
        if (getConnectionToPerson().isEmpty()) {
            addConnectionsToPersons(editedMeeting, model, existedPersonsConnection);
        } else {
            addConnectionsToPersons(editedMeeting, model, new HashSet<Person>());
        }
        // End of Reconstruct Connection.

        model.updateMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor)
            throws InvalidMeetingException {

        assert meetingToEdit != null;
        MeetingName updatedMeetingName = editMeetingDescriptor
                .getName()
                .orElse(meetingToEdit.getName());
        DateTime updatedStart = editMeetingDescriptor
                .getStart()
                .orElse(meetingToEdit.getStart());
        DateTime updatedTerminate = editMeetingDescriptor
                .getTerminate()
                .orElse(meetingToEdit.getTerminate());
        Priority updatedPriority = editMeetingDescriptor
                .getPriority()
                .orElse(meetingToEdit.getPriority());
        Description updatedDescription = editMeetingDescriptor
                .getDescription()
                .orElse(meetingToEdit.getDescription());
        Set<Group> updatedGroups = editMeetingDescriptor
                .getGroups()
                .orElse(meetingToEdit.getGroups());

        if (!Meeting.isValidStartTerminate(updatedStart, updatedTerminate)) {
            throw new InvalidMeetingException(Meeting.MESSAGE_CONSTRAINTS);
        };

        return new Meeting(updatedMeetingName, updatedStart,
                updatedTerminate, updatedPriority, updatedDescription, updatedGroups);
    }

    /**
     * This method will handle the connections that the user wants to add from both the g/ and p/
     * Duplicate person that the user wants to build connection with this meeting will be automatically removed.
     */
    private void addConnectionsToPersons(Meeting toAdd, Model model,
                                         Set<Person> existedPersonsConnection) throws CommandException {
        // Use set to ensure unique element.
        HashSet<Person> personsConnection = new HashSet<>();
        personsConnection.addAll(existedPersonsConnection);
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());

        if (!getConnectionToPerson().isEmpty()) {
            List<Person> lastShownList = model.getFilteredPersonList();
            // Check whether the index is out of bounds
            for (Index index : getConnectionToPerson()) {
                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
            }
            // If we can pass the check, then add connection.
            for (Index index: getConnectionToPerson()) {
                Person personToAddConnection = lastShownList.get(index.getZeroBased());
                personsConnection.add(personToAddConnection);
            }
        }

        for (Person allPersonToAddConnection : personsConnection) {
            model.addPersonMeetingConnection(allPersonToAddConnection, toAdd);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private MeetingName meetingName;
        private DateTime start;
        private DateTime terminate;
        private Priority priority;
        private Description description;
        private Set<Group> groups;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code groups} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setName(toCopy.meetingName);
            setStart(toCopy.start);
            setTerminate(toCopy.terminate);
            setPriority(toCopy.priority);
            setDescription(toCopy.description);
            setGroups(toCopy.groups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(meetingName, start, terminate, priority, description, groups);
        }

        public void setName(MeetingName meetingName) {
            this.meetingName = meetingName;
        }

        public Optional<MeetingName> getName() {
            return Optional.ofNullable(meetingName);
        }

        public void setStart(DateTime start) {
            this.start = start;
        }

        public Optional<DateTime> getStart() {
            return Optional.ofNullable(start);
        }

        public void setTerminate(DateTime terminate) {
            this.terminate = terminate;
        }

        public Optional<DateTime> getTerminate() {
            return Optional.ofNullable(terminate);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code groups} to this object's {@code groups}.
         * A defensive copy of {@code groups} is used internally.
         */
        public void setGroups(Set<Group> groups) {
            this.groups = (groups != null) ? new HashSet<>(groups) : null;
        }

        /**
         * Returns an unmodifiable group set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code groups} is null.
         */
        public Optional<Set<Group>> getGroups() {
            return (groups != null) ? Optional.of(Collections.unmodifiableSet(groups)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getName().equals(e.getName())
                    && getGroups().equals(e.getGroups());
        }
    }
}
