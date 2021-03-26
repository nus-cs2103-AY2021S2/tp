package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Debt;
import seedu.address.model.person.Person;

/**
 * Base Class for AddDebtCommand and SubtractDebtCommand.
 */
public class ChangeDebtCommand extends Command {

    public static final String COMMAND_WORD_ADD = "add-debt";
    public static final String COMMAND_WORD_SUBTRACT = "subtract-debt";
    public static final String MESSAGE_USAGE_ADD = String.format(ChangeDebtCommand.MESSAGE_USAGE, COMMAND_WORD_ADD);
    public static final String MESSAGE_USAGE_SUBTRACT = String.format(ChangeDebtCommand.MESSAGE_USAGE,
            COMMAND_WORD_SUBTRACT);
    public static final String MESSAGE_ADD_DEBT_SUCCESS = "Added %1$.2f to %2$s debt";
    public static final String MESSAGE_SUBTRACT_DEBT_SUCCESS = "Subtracted %1$.2f to %2$s debt";
    private static final String MESSAGE_USAGE = "%1$s: Adds the specified debt to the person identified"
            + " by the index number used in the last person listing. \n"
            + "Parameters: INDEX (must be a positive integer) DEBT \n"
            + "Example: %1$s 1 10.50";
    private final Index index;
    private final Debt debt;
    private final boolean isAdd;

    /**
     * Command that changes debt by the specified amount.
     * @param index person index in the last person listing
     * @param debt Debt to be added/subtracted
     * @param isAdd True if adding debt, subtract otherwise
     */
    public ChangeDebtCommand(Index index, Debt debt, boolean isAdd) {
        this.index = index;
        this.debt = debt;
        this.isAdd = isAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Debt currentDebt = personToEdit.getDebt();
        Person editedPerson;
        if (isAdd) {
            editedPerson = personToEdit.withDebt(Debt.add(currentDebt, debt));
        } else {
            editedPerson = personToEdit.withDebt(Debt.subtract(currentDebt, debt));
        }
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (isAdd) {
            return new CommandResult(String.format(MESSAGE_ADD_DEBT_SUCCESS, debt.value, editedPerson.getName()));
        } else {
            return new CommandResult(String.format(MESSAGE_SUBTRACT_DEBT_SUCCESS, debt.value, editedPerson.getName()));
        }
    }
}
