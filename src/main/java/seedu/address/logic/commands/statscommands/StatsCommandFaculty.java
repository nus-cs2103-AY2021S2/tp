package seedu.address.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;

public class StatsCommandFaculty extends StatsCommand {
    private Faculty faculty;

    public StatsCommandFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> studentList = model.getAddressBook().getPersonList();

        try {
            float stats = calculateRatioVaccinated(studentList, faculty);
            if (Float.isNaN(stats) || stats > 1) {
                throw new CommandException(MESSAGE_STATS_FAILURE);
            }
            return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, faculty, stats * 100));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }

    /**
     * @param studentList List of all students in Vax@NUS system.
     * @param faculty Faculty to calculate ratio vaccinated.
     * @return A float representing the ratio of number vaccinated to total students in the faculty.
     * @throws CommandException if the data is corrupted.
     */
    public static float calculateRatioVaccinated(List<Person> studentList, Faculty faculty) throws CommandException {
        int totalStudents = 0;
        int counter = 0;

        try {
            for (Person p : studentList) {
                if (p.getFaculty().equals(faculty)) {
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
                || (other instanceof StatsCommandFaculty) // instanceof handles nulls
                && faculty.equals(((StatsCommandFaculty) other).faculty); // state check
    }

}
