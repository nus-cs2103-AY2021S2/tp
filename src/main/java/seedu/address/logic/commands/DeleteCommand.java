package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.DeleteCommandParser.SELECTED;
import static seedu.address.logic.parser.DeleteCommandParser.SPECIAL_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: { shown | selected | INDEX… } (must be a valid positive integer)\n"
            + "Example:\n"
            + COMMAND_WORD + " 1\n"
            + COMMAND_WORD + " " + SPECIAL_INDEX + "\n"
            + COMMAND_WORD + " " + SELECTED;

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted person:\n%1$s";
    public static final String MESSAGE_DELETE_PERSONS_SUCCESS = "Deleted multiple persons:\n%1$s";
    public static final String MESSAGE_NO_SELECTED = "No selected person(s) to delete";
    public static final String MESSAGE_NO_PERSON = "No person(s) to delete";

    private final List<Index> targetIndexes;
    private final boolean isSpecialIndex;
    private final boolean isDeleteSelected;

    /**
     * Private constructor. Should only be called via builder.
     *
     * @param isSpecialIndex
     * @param isDeleteSelected
     * @param indexes
     */
    private DeleteCommand(boolean isSpecialIndex, boolean isDeleteSelected, List<Index> indexes) {
        targetIndexes = requireNonNull(indexes);
        this.isSpecialIndex = isSpecialIndex;
        this.isDeleteSelected = isDeleteSelected;
    }

    public static DeleteCommand buildDeleteSelectedCommand() {
        return new DeleteCommand(false, true, new ArrayList<>());
    }

    public static DeleteCommand buildDeleteShownCommand() {
        return new DeleteCommand(true, false, new ArrayList<>());
    }

    public static DeleteCommand buildDeleteIndexCommand(List<Index> indexes) {
        return new DeleteCommand(false, false, indexes);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredPersonList().size() == 0) {
            throw new CommandException(MESSAGE_NO_PERSON);
        }

        if (isSpecialIndex) {
            return deleteAll(model);
        }
        if (isDeleteSelected) {
            return deleteSelected(model);
        }
        return deleteOneOrMultiple(model);
    }

    /**
     * Deletes one or multiple person from model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if index is invalid
     */
    private CommandResult deleteOneOrMultiple(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        // Validate indexes
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<Person> personListToDelete = targetIndexes.stream()
                .map(Index::getZeroBased)
                .map(lastShownList::get)
                .collect(Collectors.toList());

        for (Person personToDelete : personListToDelete) {
            model.deletePerson(personToDelete);
            stringBuilder.append(personToDelete);
            stringBuilder.append("\n");
        }

        if (targetIndexes.size() == 1) {
            return new CommandResult(
                    String.format(MESSAGE_DELETE_PERSON_SUCCESS, stringBuilder.toString().trim()));
        }
        return new CommandResult(
                String.format(MESSAGE_DELETE_PERSONS_SUCCESS, stringBuilder.toString().trim()));
    }

    /**
     * Deletes all the person in the shown person list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    private CommandResult deleteAll(Model model) {
        List<Person> lastShownList = model.getFilteredPersonList();
        StringBuilder stringBuilder = new StringBuilder();

        // Note: There is a need to clone the array list before deleting
        for (Person personToDelete : new ArrayList<>(lastShownList)) {
            model.deletePerson(personToDelete);
            stringBuilder.append(personToDelete);
            stringBuilder.append("\n");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSONS_SUCCESS, stringBuilder));
    }

    private CommandResult deleteSelected(Model model) throws CommandException {
        model.applySelectedPredicate();
        if (model.getFilteredPersonList().size() == 0) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(MESSAGE_NO_SELECTED);
        }
        return deleteAll(model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && isSpecialIndex == ((DeleteCommand) other).isSpecialIndex
                && isDeleteSelected == ((DeleteCommand) other).isDeleteSelected
                && targetIndexes.containsAll(((DeleteCommand) other).targetIndexes))
                && ((DeleteCommand) other).targetIndexes.containsAll(targetIndexes); // state check
    }
}
