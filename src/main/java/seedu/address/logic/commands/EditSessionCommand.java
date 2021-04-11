package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Day;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.Subject;
import seedu.address.model.session.Timeslot;
import seedu.address.model.tag.Tag;

public class EditSessionCommand extends Command {
    /**
    * Edits the details of an existing session in EzManage.
    */
    public static final String COMMAND_WORD = "edit_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the session identified "
            + "by the session ID which can be found in the displayed session list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: c/ID (must be a valid session ID) "
            + "[" + PREFIX_DAY + "DAY] "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "[" + PREFIX_TIMESLOT + "TIMESLOT] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " c/1 "
            + PREFIX_DAY + "MONDAY "
            + PREFIX_SUBJECT + "Biology";

    public static final String MESSAGE_EDIT_SESSION_SUCCESS = "Edited Session: ";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final SessionId sessionId;
    private final EditSessionDescriptor editSessionDescriptor;

    /**
     * @param sessionId            of the session in the filtered session list to edit
     * @param editSessionDescriptor details to edit the session with
     */
    public EditSessionCommand(SessionId sessionId, EditSessionDescriptor editSessionDescriptor) {
        requireNonNull(sessionId);
        requireNonNull(editSessionDescriptor);

        this.sessionId = sessionId;
        this.editSessionDescriptor = new EditSessionDescriptor(editSessionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Session> lastShownList = model.getUnfilteredSessionList();

        Optional<Session> optSessionToEdit = lastShownList.stream()
                .filter(x-> x.getClassId().equals(sessionId)).findAny();

        boolean hasDayOrTime = this.editSessionDescriptor.hasDay() || this.editSessionDescriptor.hasTimeslot();
        boolean emptySession = emptySession(optSessionToEdit);

        if ((!emptySession) && hasDayOrTime) {
            throw new CommandException(Messages.MESSAGE_CANNOT_EDIT);
        } else if (optSessionToEdit.isPresent()) {
            Session sessionToEdit = optSessionToEdit.get();
            Session editedSession = createEditedSession(sessionToEdit, editSessionDescriptor);
            model.setSession(sessionToEdit, editedSession);
            model.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
            return new CommandResult(MESSAGE_EDIT_SESSION_SUCCESS + String.format(Messages
                                .MESSAGE_SESSION_PLACEHOLDER, editedSession));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

    }

    /**
     * Checks if session to be edited has existing tutor assigned or students assigned.
     * Method only used if either session's day or timeslot is to be edited.
     */

    private static boolean emptySession(Optional<Session> sessionToEdit) {
        if (!sessionToEdit.isPresent()) {
            return true;
        } else if (sessionToEdit.get().emptyTutor()
                && sessionToEdit.get().emptyStudentList()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates and returns a {@code Session} with the details of {@code sessionToEdit}
     * edited with {@code editSessionDescriptor}.
     */
    private static Session createEditedSession(Session sessionToEdit, EditSessionDescriptor editSessionDescriptor) {
        assert sessionToEdit != null;

        SessionId sessionId = sessionToEdit.getClassId();
        Day updatedDay = editSessionDescriptor.getDay().orElse(sessionToEdit.getDay());
        Subject updatedSubject = editSessionDescriptor.getSubject().orElse(sessionToEdit.getSubject());
        Timeslot updatedTimeSlot = editSessionDescriptor.getTimeSlot().orElse(sessionToEdit.getTimeslot());
        Set<Tag> updatedTags = editSessionDescriptor.getTags().orElse(sessionToEdit.getTags());

        Session newSession = new Session(sessionId, updatedDay, updatedTimeSlot, updatedSubject, updatedTags);
        newSession.setStudents(sessionToEdit.getStudents());
        newSession.setTutor(sessionToEdit.getTutor());
        return newSession;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSessionCommand)) {
            return false;
        }

        // state check
        EditSessionCommand e = (EditSessionCommand) other;
        return sessionId.equals(e.sessionId)
                && editSessionDescriptor.equals(e.editSessionDescriptor);
    }

    /**
     * Stores the details to edit the session with. Each non-empty field value will replace the
     * corresponding field value of the session. All fields can be edited except the session ID.
     */
    public static class EditSessionDescriptor {
        private Day day;
        private Subject subject;
        private Timeslot timeslot;
        private Set<Tag> tags;
        private SessionId sessionId;
        public EditSessionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSessionDescriptor(EditSessionDescriptor toCopy) {
            setDay(toCopy.day);
            setSubject(toCopy.subject);
            setTimeSlot(toCopy.timeslot);
            setTags(toCopy.tags);
            setSessionId(toCopy.sessionId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(day, subject, timeslot, tags);
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public Optional<Day> getDay() {
            return Optional.ofNullable(day);
        }

        public boolean hasDay() {
            return Optional.ofNullable(day).isPresent();
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Optional<Subject> getSubject() {
            return Optional.ofNullable(subject);
        }

        public void setTimeSlot(Timeslot timeslot) {
            this.timeslot = timeslot;
        }

        public Optional<Timeslot> getTimeSlot() {
            return Optional.ofNullable(timeslot);
        }

        public boolean hasTimeslot() {
            return Optional.ofNullable(timeslot).isPresent();
        }

        public void setSessionId(SessionId sessionId) {
            this.sessionId = sessionId;
        }

        public Optional<SessionId> getSessionId() {
            return Optional.ofNullable(sessionId);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSessionDescriptor)) {
                return false;
            }

            // state check
            EditSessionDescriptor e = (EditSessionDescriptor) other;

            return getDay().equals(e.getDay())
                    && getSubject().equals(e.getSubject())
                    && getTimeSlot().equals(e.getTimeSlot())
                    && getSessionId().equals(e.getSessionId())
                    && getTags().equals(e.getTags());
        }
    }
}
