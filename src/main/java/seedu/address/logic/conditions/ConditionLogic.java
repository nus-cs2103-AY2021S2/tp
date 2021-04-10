package seedu.address.logic.conditions;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;

public class ConditionLogic {
    private final Task task;
    private final DateVerifier dateVerifier;
    private final ConstraintManager constraintManager;

    public ConditionLogic(Task task) {
        this.task = task;
        this.dateVerifier = new DateVerifier(task);
        this.constraintManager = new ConstraintManager(task);
    }

    public static void verifyIndex(Index index, List<Task> lastShownList) throws CommandException {
        IndexManager.verifyIndex(index, lastShownList);
    }

    public void checkInvalidDateRange() throws CommandException {
        dateVerifier.checkInvalidDateRange();
    }

    public void checkForExpiredDate() throws CommandException {
        dateVerifier.checkForExpiredDate();
    }

    public void enforceAttributeConstraints() throws CommandException {
        constraintManager.enforceAttributeConstraints();
    }

    public void enforceTitleLength() throws CommandException {
        constraintManager.enforceTitleLength();
    }

}
