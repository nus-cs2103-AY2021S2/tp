package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ListType.PERSON_TYPE_LIST;
import static seedu.address.logic.parser.ListType.SESSION_TYPE_LIST;
import static seedu.address.logic.parser.ListType.STUDENT_TYPE_LIST;
import static seedu.address.logic.parser.ListType.TUTOR_TYPE_LIST;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;

import java.util.function.Predicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all persons, students, tutors or sessions.\n"
            + "Parameters: persons OR students OR tutors OR sessions \n"
            + "Example: " + COMMAND_WORD + " persons \n"
            + "Example: " + COMMAND_WORD + " tutors";

    public static final String MESSAGE_SUCCESS_PERSONS = "Listed all persons";
    public static final String MESSAGE_SUCCESS_SESSIONS = "Listed all sessions";
    public static final String MESSAGE_SUCCESS_STUDENTS = "Listed all students";
    public static final String MESSAGE_SUCCESS_TUTORS = "Listed all tutors";
    private final Predicate<Person> personPredicate;
    private final Predicate<Session> sessionPredicate;
    private final String listCommandType;

    public ListCommand(Predicate<Person> personPredicate, Predicate<Session> sessionPredicate,
                       String listCommandType) {
        this.personPredicate = personPredicate;
        this.sessionPredicate = sessionPredicate;
        this.listCommandType = listCommandType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(personPredicate);
        model.updateFilteredSessionList(sessionPredicate);

        switch (listCommandType) {

            case PERSON_TYPE_LIST:
                return new CommandResult(MESSAGE_SUCCESS_PERSONS);

            case STUDENT_TYPE_LIST:
                return new CommandResult(MESSAGE_SUCCESS_STUDENTS);

            case TUTOR_TYPE_LIST:
                return new CommandResult(MESSAGE_SUCCESS_TUTORS);

            case SESSION_TYPE_LIST:
                return new CommandResult(MESSAGE_SUCCESS_SESSIONS);

            default:
                throw new CommandException(MESSAGE_USAGE);
        }
    }
}
