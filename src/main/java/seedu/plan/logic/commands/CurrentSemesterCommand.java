package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.Model;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.plan.Semester;

/**
 * Marks a Semester as the current semester.
 */
public class CurrentSemesterCommand extends Command {

    public static final String COMMAND_WORD = "current";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the supplied semester as the current one. "
            + "Parameters: "
            + PREFIX_SEM_NUMBER + "SEM_NUMBER";

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
                model.setCurrentSemester(Integer.valueOf(semester.getSemNumber()));
                return new CommandResult(String.format(MESSAGE_SUCCESS, currentSemesterNumber));
            }
        }
        throw new CommandException("The provided SEM_NUMBER does not match any existing semesters in the "
                + "master plan.");
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
