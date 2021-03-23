package seedu.student.logic.commands.statscommands;

import seedu.student.logic.commands.Command;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.SchoolResidence;

public abstract class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_STATS_SUCCESS = "Percentage %s vaccinated: %.2f%%";
    public static final String MESSAGE_STATS_FAILURE = "No available data on given faculty/residence.";

    private static String stringResidences = SchoolResidence.getStringResidences();
    private static String stringFaculties = Faculty.getStringFaculties();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists statistics of category given. Please enter only one parameter. \n"
            + "Parameters: \n"
            + stringResidences + "\n"
            + stringFaculties + "\n"
            + "Example: " + COMMAND_WORD + " COM";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
