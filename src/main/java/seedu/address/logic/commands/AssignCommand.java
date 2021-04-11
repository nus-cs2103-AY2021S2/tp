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
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;

public class AssignCommand extends Command {
    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns student(s) and/or tutor to a class \n"
            + "Parameters: " + "(assign student ONLY, tutor Only or Both) "
            + PREFIX_STUDENT_ID + "StudentId "
            + PREFIX_TUTOR_ID + "TutorId "
            + PREFIX_CLASS_ID + "ClassId \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "3 "
            + PREFIX_TUTOR_ID + "2 "
            + PREFIX_CLASS_ID + "1 ";

    public static final String MESSAGE_SUCCESS = "Assign: %1$s";
    public static final String MESSAGE_TIMESLOT_CLASH = "Timeslot clash! Cannot assign: %1$s to %2$s";
    public static final String MESSAGE_ALREADY_ASSIGNED = "%1$s is already assigned to %2$s";
    public static final String MESSAGE_SESSION_HAS_TUTOR = "Cannot assign! %1$s already has tutor %2$s";

    private final Assignment assignment;

    /**
     * Creates an AddSessionCommand to add the specified {@code Person}
     */
    public AssignCommand(Assignment assignment) {
        requireNonNull(assignment);
        this.assignment = assignment;
    }

    private boolean isAssignedTo(Person person, Session sessionToCheck) {
        for (SessionId sessionId : person.getSessions()) {
            if (sessionId.equals(sessionToCheck.getClassId())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasClashes(Model model, Person person, Session sessionToCheck) {
        for (SessionId sessionId : person.getSessions()) {
            List<Session> lastShownSessionsList = model.getUnfilteredSessionList();
            Session currentSession = lastShownSessionsList.stream()
                    .filter(x-> x.getClassId().equals(sessionId)).findAny().get();

            if (currentSession.isClashingWith(sessionToCheck)) {
                return true;
            }
        }
        return false;
    }

    private void checkStudentSession(Model model, Person student, Session session) throws CommandException {
        assert(student.isStudent());
        if (isAssignedTo(student, session)) {
            throw new CommandException(String.format(MESSAGE_ALREADY_ASSIGNED,
                    student.getPersonId(), session.getClassId()));
        }
        if (hasClashes(model, student, session)) {
            throw new CommandException(String.format(MESSAGE_TIMESLOT_CLASH,
                    student.getPersonId(), session.getClassId()));
        }
    }

    private void checkTutorSession(Model model, Person tutor, Session session) throws CommandException {
        assert(tutor.isTutor());
        if (session.getTutor().equals(tutor.getPersonId())) {
            throw new CommandException(String.format(MESSAGE_ALREADY_ASSIGNED,
                    tutor.getPersonId(), session.getClassId()));
        }
        if (session.hasTutor()) {
            throw new CommandException(String.format(MESSAGE_SESSION_HAS_TUTOR,
                    session.getClassId(), session.getTutor()));
        }
        if (hasClashes(model, tutor, session)) {
            throw new CommandException(String.format(MESSAGE_TIMESLOT_CLASH,
                    tutor.getPersonId(), session.getClassId()));
        }
    }

    private Set<Person> getStudents(Model model, Set<PersonId> studentIds, Session session) throws CommandException {
        List<Person> lastShownPersonsList = model.getUnfilteredPersonList();
        Set<Person> studentsToAssign = new HashSet<>();
        for (PersonId studentId : studentIds) {
            Optional<Person> studentToAssign = lastShownPersonsList.stream()
                    .filter(x-> x.getPersonId().equals(studentId)).findAny();
            if (studentToAssign.isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person student = studentToAssign.get();
            try {
                checkStudentSession(model, student, session);
            } catch (CommandException e) {
                throw new CommandException(e.getMessage());
            }
            studentsToAssign.add(student);
        }
        return studentsToAssign;
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
            checkTutorSession(model, tutor, session);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }
        return tutor;
    }

    private void assignStudents(Set<Person> students, Session session) {
        for (Person student : students) {
            assert(student.isStudent());
            student.addSession(session.getClassId());
            session.assignStudent(student.getPersonId());
        }
    }

    private void assignTutor(Person tutor, Session session) {
        assert(tutor.isTutor());
        tutor.addSession(session.getClassId());
        session.assignTutor(tutor.getPersonId());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<PersonId> studentIds = this.assignment.getStudentIds();
        PersonId tutorId = this.assignment.getTutorId();
        SessionId sessionId = this.assignment.getSessionId();

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
            assignTutor(tutor, session);
        }

        assignStudents(students, session);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, assignment));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && assignment.equals(((AssignCommand) other).assignment));
    }
}
