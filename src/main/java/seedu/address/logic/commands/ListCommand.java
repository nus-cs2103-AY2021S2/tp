package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ListType.PERSON_TYPE_LIST;
import static seedu.address.logic.parser.ListType.SESSION_TYPE_LIST;
import static seedu.address.logic.parser.ListType.STUDENT_TYPE_LIST;
import static seedu.address.logic.parser.ListType.TUTOR_TYPE_LIST;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;

/**
 * Lists all persons in EzManage to the user.
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
    public static final String MESSAGE_EMPTY_PERSON_LIST = "The list of persons is empty!";
    public static final String MESSAGE_EMPTY_SESSION_LIST = "The list of sessions is empty!";
    public static final String MESSAGE_EMPTY_STUDENT_LIST = "The list of students is empty!";
    public static final String MESSAGE_EMPTY_TUTOR_LIST = "The list of tutors is empty!";

    private final Predicate<Person> personPredicate;
    private final Predicate<Session> sessionPredicate;
    private final String listCommandType;

    /**
     * Creates a ListCommand with a specific list type.
     * @param personPredicate updates the filteredPersonList.
     * @param sessionPredicate updates the filteredSessionList.
     * @param listCommandType type of list to be shown.
     */
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
            if (model.emptyPersonList()) {
                return new CommandResult(MESSAGE_EMPTY_PERSON_LIST);
            } else {
                return new CommandResult(MESSAGE_SUCCESS_PERSONS);
            }

        case STUDENT_TYPE_LIST:
            if (model.emptyPersonList()) {
                return new CommandResult(MESSAGE_EMPTY_STUDENT_LIST);
            } else {
                return new CommandResult(MESSAGE_SUCCESS_STUDENTS);
            }

        case TUTOR_TYPE_LIST:
            if (model.emptyPersonList()) {
                return new CommandResult(MESSAGE_EMPTY_TUTOR_LIST);
            } else {
                return new CommandResult(MESSAGE_SUCCESS_TUTORS);

            }

        case SESSION_TYPE_LIST:
            if (model.emptySessionList()) {
                return new CommandResult(MESSAGE_EMPTY_SESSION_LIST);
            } else {
                return new CommandResult(MESSAGE_SUCCESS_SESSIONS);
            }

        default:
            throw new CommandException(MESSAGE_USAGE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && listCommandType.equals(((ListCommand) other).listCommandType)); // state check
    }
}
