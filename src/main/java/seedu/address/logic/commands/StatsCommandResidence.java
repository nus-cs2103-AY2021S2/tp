package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.VaccinationStatus;

public class StatsCommandResidence extends StatsCommand {
    private SchoolResidence residence;

    public StatsCommandResidence(SchoolResidence residence) {
        this.residence = residence;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> studentList = model.getAddressBook().getPersonList();

        try {
            float stats = calculateRatioVaccinated(studentList, residence);
            if (Float.isNaN(stats) || stats > 1) {
                throw new CommandException(MESSAGE_STATS_FAILURE);
            }
            return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, residence, stats * 100));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }

    /**
     * @param stuList List of all students in Vax@NUS system.
     * @param resi School residence to calculate ratio vaccinated.
     * @return A float representing the ratio of number vaccinated to total students in the residence.
     * @throws CommandException if the data is corrupted.
     */
    public static float calculateRatioVaccinated(List<Person> stuList, SchoolResidence resi) throws CommandException {
        VaccinationStatus vaccinated = new VaccinationStatus("vaccinated");
        int totalStudents = 0;
        int counter = 0;
        try {
            for (Person p : stuList) {
                if (p.getSchoolResidence().equals(resi)) {
                    totalStudents++;
                    if (p.getVaccinationStatus().equals(vaccinated)) {
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
}
