package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Goal.Frequency;
import seedu.address.model.person.Person;

public class SetGoalCommand extends Command {

    public static final String COMMAND_WORD = "set-goal";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set goals for meeting the person identified by the index.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "f/(w[eek[ly]] | m[onth[ly]] | y[ear[ly]] | n[one])\n"
            + "Example: " + COMMAND_WORD + " 1 f/w";

    public static final String MESSAGE_ADD_GOAL_SUCCESS = "Set %1$s goal for %2$s";

    private final Index index;
    private final Frequency frequency;

    /**
     * Constructs an {@code AddGoalCommand} with the given index and frequency.
     * @param index of the person in the filter person list to add a goal for.
     * @param frequency of the meetings with this person.
     */
    public SetGoalCommand(Index index, Frequency frequency) {
        this.index = index;
        this.frequency = frequency;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size() || index.getOneBased() <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX_ARGUMENT);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Goal newGoal = new Goal(this.frequency);
        Person editedPerson = personToEdit.withGoal(newGoal);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateUpcomingDates();

        return new CommandResult(String.format(MESSAGE_ADD_GOAL_SUCCESS,
                newGoal.toString().toLowerCase(), editedPerson.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SetGoalCommand that = (SetGoalCommand) o;

        if (!Objects.equals(index, that.index)) {
            return false;
        }
        return frequency == that.frequency;
    }

    @Override
    public int hashCode() {
        int result = index != null ? index.hashCode() : 0;
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        return result;
    }
}
