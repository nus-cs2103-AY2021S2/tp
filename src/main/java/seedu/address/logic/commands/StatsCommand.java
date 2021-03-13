package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.VaccinationStatus;

public class StatsCommand extends Command {

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
    public CommandResult execute(Model model) throws CommandException { // all
        requireNonNull(model);
        List<Person> studentList = model.getAddressBook().getPersonList();
        VaccinationStatus vaccinated = new VaccinationStatus("vaccinated");
        int totalStudents = studentList.size();
        int counter = 0;

        try {
            for (Person p : studentList) {
                if (p.getVaccinationStatus().equals(vaccinated)) {
                    counter++;
                }
            }
            float stats = (float) counter / totalStudents;
            return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, stats * 100));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }
}
