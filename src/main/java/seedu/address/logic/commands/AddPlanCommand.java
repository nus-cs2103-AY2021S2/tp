package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.insurance.InsurancePlan;
import seedu.address.model.person.Person;

/**
 * Adds an insurance plan to a client.
 */
public class AddPlanCommand extends PlanCommand {

    public static final String MESSAGE_ADD_PLAN_SUCCESS = "Added %1$s insurance plan to %2$s";

    private final InsurancePlan plan;

    /**
     * Constructor for AddPlanCommand
     */
    public AddPlanCommand(Index targetIndex, InsurancePlan plan) {
        super(targetIndex);
        this.plan = plan;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Index targetIndex = getTargetIndex();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person original = lastShownList.get(targetIndex.getZeroBased());
        Person updated = original.addPlan(plan);
        model.setPerson(original, updated);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        String result = String.format(MESSAGE_ADD_PLAN_SUCCESS, plan.toString(), updated.getName());
        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPlanCommand // instanceof handles nulls
                && getTargetIndex().equals(((AddPlanCommand) other).getTargetIndex()) // state check
                && plan.equals(((AddPlanCommand) other).plan));
    }
}
