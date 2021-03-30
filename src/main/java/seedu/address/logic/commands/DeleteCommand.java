package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
            + "Parameters: INDEX [INDEX…] (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted person: %1$s";
    public static final String MESSAGE_DELETE_PERSONS_SUCCESS = "Deleted multiple person:\n%1$s";

    private final List<Index> targetIndexes;
    private final boolean isSpecialIndex;

    /**
     * Initializes DeleteCommand that deletes all the shown items in list.
     */
    public DeleteCommand() {
        targetIndexes = new ArrayList<>();
        isSpecialIndex = true;
    }

    /**
     * Initializes DeleteCommand with a list of parsed user input indexes.
     *
     * @param indexes parsed user input indexes
     * @throws NullPointerException if {@code indexes} is null.
     */
    public DeleteCommand(List<Index> indexes) {
        targetIndexes = requireNonNull(indexes);
        isSpecialIndex = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isSpecialIndex) {
            return deleteAll(model);
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
        return new CommandResult(String.format(MESSAGE_DELETE_PERSONS_SUCCESS, stringBuilder));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.containsAll(((DeleteCommand) other).targetIndexes)); // state check
    }
}
