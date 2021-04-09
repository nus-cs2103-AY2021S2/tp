package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Unassignment;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;

public class UnassignCommand extends Command {
    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns student(s) and/or tutor from a class \n"
            + "Parameters: " + "(assign student ONLY, tutor Only or Both) "
            + PREFIX_STUDENT_ID + "StudentId "
            + PREFIX_TUTOR_ID + "TutorId "
            + PREFIX_CLASS_ID + "ClassId \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "3 "
            + PREFIX_TUTOR_ID + "2 "
            + PREFIX_CLASS_ID + "1 ";

    public static final String MESSAGE_SUCCESS = "Unassigned: %1$s";
    public static final String MESSAGE_NOT_ASSIGNED = "%1$s is not assigned to %2$s. Cannot unassign";
    public static final String MESSAGE_NO_TUTOR = "Cannot unassign %1$s from %2$s. %2$s currently has no tutor.";
    public static final String MESSAGE_DIFFERENT_TUTOR = "Cannot unassign %1$s from %2$s. Current tutor is %3$s";

    private final Unassignment unassignment;

    /**
     * Creates an AddSessionCommand to add the specified {@code Person}
     */
    public UnassignCommand(Unassignment unassignment) {
        requireNonNull(unassignment);
        this.unassignment = unassignment;
    }

    private boolean isAssignedTo(Person person, Session sessionToCheck) {
        for (SessionId sessionId : person.getSessions()) {
            if (sessionId.equals(sessionToCheck.getClassId())) {
                return true;
            }
        }
        return false;
    }

    private void checkTutorSession(Person tutor, Session session) throws CommandException {
        assert(tutor.isTutor());
        if (!session.hasTutor()) {
            throw new CommandException(String.format(MESSAGE_NO_TUTOR,
                    tutor.getPersonId(), session.getClassId()));
        }
        if (!session.getTutor().equals(tutor.getPersonId())) {
            throw new CommandException(String.format(MESSAGE_DIFFERENT_TUTOR,
                    tutor.getPersonId(), session.getClassId(), session.getTutor()));
        }
    }

    private Set<Person> getStudents(Model model, Set<PersonId> studentIds, Session session) throws CommandException {
        Set<Person> studentsToUnassign = new HashSet<>();
        List<Person> lastShownPersonsList = model.getUnfilteredPersonList();
        for (PersonId studentId : studentIds) {
            Optional<Person> studentToAssign = lastShownPersonsList.stream()
                    .filter(x-> x.getPersonId().equals(studentId)).findAny();
            if (studentToAssign.isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person student = studentToAssign.get();
            if (!isAssignedTo(student, session)) {
                throw new CommandException(String.format(MESSAGE_NOT_ASSIGNED, studentId, session.getClassId()));
            }
            studentsToUnassign.add(student);
        }
        return studentsToUnassign;
    }

    private Person getTutor(Model model, PersonId tutorId, Session session) throws CommandException {
        List<Person> lastShownPersonsList = model.getUnfilteredPersonList();
        Optional<Person> tutorToAssign = lastShownPersonsList.stream()
                .filter(x-> x.getPersonId().equals(tutorId)).findAny();
        if (tutorToAssign.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person tutor = tutorToAssign.get();
        try {
            checkTutorSession(tutor, session);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }
        return tutor;
    }

    private void unassignStudents(Set<Person> students, Session session) {
        for (Person student : students) {
            assert(student.isStudent());
            student.removeSession(session.getClassId());
            session.unassignStudent(student.getPersonId());
        }
    }

    private void unassignTutor(Person tutor, Session session) {
        assert(tutor.isTutor());
        tutor.removeSession(session.getClassId());
        session.unassignTutor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<PersonId> studentIds = this.unassignment.getStudentIds();
        PersonId tutorId = this.unassignment.getTutorId();
        SessionId sessionId = this.unassignment.getSessionId();

        List<Session> lastShownSessionsList = model.getUnfilteredSessionList();
        Optional<Session> sessionToAssign = lastShownSessionsList.stream()
                .filter(x-> x.getClassId().equals(sessionId)).findAny();
        if (sessionToAssign.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }
        Session session = sessionToAssign.get();
        Set<Person> students = getStudents(model, studentIds, session);

        if (tutorId != null) {
            Person tutor = getTutor(model, tutorId, session);
            unassignTutor(tutor, session);
        }

        unassignStudents(students, session);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, unassignment));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && unassignment.equals(((UnassignCommand) other).unassignment));
    }
}
