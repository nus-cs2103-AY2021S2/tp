package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.PersonIdPredicate;
import seedu.address.model.person.PersonPredicate;
import seedu.address.model.person.PersonTypePredicate;



public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View all students or tutors, or view a specific person by personID.\n"
            + "Parameters: STUDENT or TUTOR or PERSON_ID\n"
            + "Example: " + COMMAND_WORD + " STUDENT\n"
            + "Example: " + COMMAND_WORD + " s/1";

    public static final String MESSAGE_SUCCESS = "Displayed all relevant persons.";
    public static final String MESSAGE_NO_PERSON_FOUND = "There is no person found under the specified parameter!";
    private final PersonPredicate predicate;

    public ViewCommand(PersonTypePredicate predicate) {
        this.predicate = predicate;
    }

    public ViewCommand(PersonIdPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(MESSAGE_NO_PERSON_FOUND);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
