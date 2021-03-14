package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;

/**
 * Marks a Semester as the current semester.
 */
public class CurrentSemesterCommand extends Command {

    public static final String COMMAND_WORD = "current";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "s/SEM_NUMBER\n: " +
            "Marks the supplied semester as the current one.";

    public static final String MESSAGE_SUCCESS = "Successfully marked current semester: %1$d";

    private final int currentSemesterNumber;

    /**
     * Creates a CurrentSemesterCommand to mark the given semester number as being the current one.
     *
     * @param currentSemesterNumber The semester number, in the master plan, to be marked as the current one.
     */
    public CurrentSemesterCommand(int currentSemesterNumber) {
        this.currentSemesterNumber = currentSemesterNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Plan masterPlan = model.getMasterPlan();
        for (Semester semester : masterPlan.getSemesters()) {
            if (semester.getSemNumber() == currentSemesterNumber) {
                model.setCurrentSemester(semester.getSemNumber());
                break;
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, currentSemesterNumber));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof CurrentSemesterCommand) {
            CurrentSemesterCommand o = (CurrentSemesterCommand) other;
            return this.currentSemesterNumber == o.currentSemesterNumber;
        } else {
            return false;
        }
    }
}
