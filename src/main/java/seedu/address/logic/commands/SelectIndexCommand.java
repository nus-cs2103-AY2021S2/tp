package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Selects person(s) in the person list.
 */
public class SelectIndexCommand extends SelectCommand {

    public static final String MESSAGE_SHOWN_SUCCESS = "Selected all shown items";
    public static final String MESSAGE_INDEX_SUCCESS = "Selected items";

    private final List<Index> selectedIndexes;
    private final boolean isSpecialIndex;

    /**
     * Initializes SelectIndexCommand with a list of parsed user input indexes.
     *
     * @param indexes parsed user input indexes
     * @throws NullPointerException if {@code indexes} is null.
     */
    public SelectIndexCommand(List<Index> indexes) {
        selectedIndexes = requireNonNull(indexes);
        isSpecialIndex = false;
    }

    /**
     * Initializes SelectIndexCommand that selects all the shown items in list.
     */
    public SelectIndexCommand() {
        selectedIndexes = new ArrayList<>();
        isSpecialIndex = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isSpecialIndex) {
            List<Person> personList = model.getFilteredPersonList();
            model.updateSelectedPersonList(personList);
            return new CommandResult(MESSAGE_SHOWN_SUCCESS);
        }

        List<Person> personList = model.getSortedFilteredPersonList();
        List<Person> selectedPersonList = new ArrayList<>();
        for (Index index : selectedIndexes) {
            if (index.getZeroBased() >= personList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person selectedPerson = personList.get(index.getZeroBased());
            selectedPersonList.add(selectedPerson);
        }
        model.updateSelectedPersonList(selectedPersonList);
        return new CommandResult(MESSAGE_INDEX_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectIndexCommand // instanceof handles nulls
                && ((SelectIndexCommand) other).selectedIndexes.containsAll(selectedIndexes)
                && isSpecialIndex == ((SelectIndexCommand) other).isSpecialIndex);
    }
}
