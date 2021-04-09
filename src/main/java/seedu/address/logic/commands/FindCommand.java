package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_PATIENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_MAIN_PATIENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Patient;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_DISPLAYED_IN_VIEW_PATIENT_BOX = "No results. Use 'list' to see your patients"
                                                                        + " and try again.";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();
        Patient checkPatient;
        Patient firstPatient;
        String displayMessage;
        try {
            checkPatient = lastShownList.get(0);
            if (checkPatient.isArchived()) {
                model.updateFilteredPersonList(predicate.and(PREDICATE_SHOW_ARCHIVED_PATIENTS));
            } else {
                model.updateFilteredPersonList(predicate.and(PREDICATE_SHOW_MAIN_PATIENTS));
            }
            firstPatient = model.getFilteredPersonList().get(0);
            model.selectPatient(firstPatient);
            displayMessage = null;
        } catch (IndexOutOfBoundsException e) {
            model.updateFilteredPersonList(predicate.and(PREDICATE_SHOW_MAIN_PATIENTS));
            firstPatient = null;
            displayMessage = MESSAGE_DISPLAYED_IN_VIEW_PATIENT_BOX;
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, false, firstPatient, null, null, displayMessage, false);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
