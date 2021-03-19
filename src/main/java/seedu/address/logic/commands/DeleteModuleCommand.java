package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SEM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;

/**
 * Deletes a module from the plan and semester identified using it's displayed index from the address book.
 */
public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "deletem";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a Module from the semester identified by the semester number and plan number.\n"
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN NUMBER"
            + PREFIX_SEM_NUMBER + "SEM NUMBER"
            + PREFIX_MODULE_CODE + "MODULE CODE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAN_NUMBER + "1 "
            + PREFIX_SEM_NUMBER + "1"
            + PREFIX_MODULE_CODE + "CS1101S";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Module deleted from Semester %1$s, Plan %2$s";

    private final Index targetPlanIndex;
    private final Index targetSemesterIndex;
    private final String targetModuleCode;

    /**
     * Creates a DeleteModuleCommand to delete the specified {@code Module}
     */
    public DeleteModuleCommand(Index targetPlanIndex, Index targetSemesterIndex, String targetModuleCode) {
        this.targetPlanIndex = targetPlanIndex;
        this.targetSemesterIndex = targetSemesterIndex;
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownList = model.getFilteredPlanList();

        if (targetPlanIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
        }

        Plan planToDeleteFrom = lastShownList.get(targetPlanIndex.getZeroBased());

        if (!planToDeleteFrom.hasSemester(targetSemesterIndex.getOneBased())) {
            throw new CommandException(MESSAGE_INVALID_SEM_NUMBER);
        }

        Semester semesterToDeleteFrom = planToDeleteFrom.getSemester(targetSemesterIndex.getOneBased());

        if (!semesterToDeleteFrom.hasModule(targetModuleCode)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_CODE_IN_SEMESTER);
        }
        Module toDeleteModule = semesterToDeleteFrom.getModuleByModuleCode(targetModuleCode);
//        System.out.println(planToDeleteFrom);
//        System.out.println(semesterToDeleteFrom);
        model.deleteModule(planToDeleteFrom, semesterToDeleteFrom, toDeleteModule);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS,
                targetSemesterIndex.getOneBased(), targetPlanIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSemesterCommand // instanceof handles nulls
                && targetPlanIndex == (((DeleteModuleCommand) other).targetPlanIndex)
                && targetSemesterIndex == (((DeleteModuleCommand) other).targetSemesterIndex)
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode));
    }
}
