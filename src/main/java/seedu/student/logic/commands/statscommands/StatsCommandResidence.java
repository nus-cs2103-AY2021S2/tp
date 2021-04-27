package seedu.student.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;

/**
 * Calculates percentages of students vaccinated from each residence.
 */
public class StatsCommandResidence extends StatsCommand {
    private SchoolResidence residence;

    /**
     * Creates a StatsCommandResidence object responsible for calculating the percentage of the specified residence
     * that is vaccinated.
     *
     * @param residence Residence you want to view percentage vaccinated.
     */
    public StatsCommandResidence(SchoolResidence residence) {
        this.residence = residence;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getStudentList();

        try {
            float stats = calculateRatioVaccinated(studentList, residence);
            if (Float.isNaN(stats) || stats > 1) {
                throw new CommandException(MESSAGE_STATS_FAILURE);
            }
            return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, residence.toString(), stats * 100));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }

    /**
     * @param studentList List of all students in Vax@NUS system.
     * @param residence School residence to calculate ratio vaccinated.
     * @return A float representing the ratio of number vaccinated to total students in the residence.
     * @throws CommandException if the data is corrupted.
     */
    public static float calculateRatioVaccinated(List<Student> studentList, SchoolResidence residence)
            throws CommandException {
        int totalStudents = 0;
        int counter = 0;
        try {
            for (Student p : studentList) {
                if (p.getSchoolResidence().equals(residence)) {
                    totalStudents++;
                    if (p.isVaccinated()) {
                        counter++;
                    }
                }
            }
            float stats = (float) counter / totalStudents;
            return stats;
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsCommandResidence) // instanceof handles nulls
                && residence.equals(((StatsCommandResidence) other).residence); // state check
    }
}
