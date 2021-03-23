package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MatriculationNumberContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "deleteStud";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their unique matriculation number assigned by NUS.\n"
            + "Parameters: Matriculation Number \n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s"; // add name + matric number

    private final MatriculationNumber matriculationNumber;
    private final MatriculationNumberContainsKeywordsPredicate predicate;


    /**
     * Creates a DeleteCommand object responsible for deleting a student by matriculation number.
     *
     * @param matriculationNumber Matriculation number of the student you want to delete.
     */
    public DeleteCommand(MatriculationNumber matriculationNumber) {
        this.matriculationNumber = matriculationNumber;

        predicate = new MatriculationNumberContainsKeywordsPredicate(matriculationNumber.toString());
    }

    /**
     * @param personList List of all students in Vax@NUS system.
     * @param matricNum Matriculation Number of the student you want to delete.
     * @return Person you want to delete, null if the matriculation number does not exist in System.
     */
    public static Person getPerson(List<Person> personList, MatriculationNumber matricNum) {
        assert personList != null;
        assert MatriculationNumber.isValidMatric(matricNum.value);
        for (Person p : personList) {
            if (p.getMatriculationNumber().equals(matricNum)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> studentList = model.getAddressBook().getPersonList();

        Person personToDelete = getPerson(studentList, matriculationNumber);
        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && matriculationNumber.equals(((DeleteCommand) other).matriculationNumber)); // state check
    }
}
