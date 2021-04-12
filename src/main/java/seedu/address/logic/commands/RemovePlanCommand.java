package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Removes an insurance plan of a client.
 */
public class RemovePlanCommand extends PlanCommand {

    public static final String MESSAGE_REMOVE_PLAN_SUCCESS = "Removed %1$s insurance plan from %2$s";

    private final Index planIndex;

    /**
     * Constructor for RemovePlanCommand
     */
    public RemovePlanCommand(Index targetIndex, Index planIndex) {
        super(targetIndex);
        this.planIndex = planIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Index targetIndex = getTargetIndex();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException("The client index provided is invalid.");
        }

        Person personToSchedule = lastShownList.get(targetIndex.getZeroBased());
        if (planIndex.getZeroBased() >= personToSchedule.getPlans().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_INDEX);
        }
        String planString = personToSchedule.getPlanString(planIndex.getZeroBased());
        Person updatedPerson = personToSchedule.removePlan(planIndex.getZeroBased());
        model.setPerson(personToSchedule, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        String result = String.format(MESSAGE_REMOVE_PLAN_SUCCESS, planString, updatedPerson.getName());
        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemovePlanCommand // instanceof handles nulls
                && getTargetIndex().equals(((RemovePlanCommand) other).getTargetIndex()) // state check
                && planIndex.equals(((RemovePlanCommand) other).planIndex));
    }
}
