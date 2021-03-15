package seedu.address.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;
import seedu.address.model.person.SchoolResidence;

public class StatsCommandAll extends StatsCommand {
    public static final String MESSAGE_STATS_SUCCESS = "%s: %.2f%%";

    private static final String PADDING = "   ";

    private List<String> listResidences = SchoolResidence.getResidenceAbbreviation();
    private List<String> listFaculties = Faculty.getFacultyAbbreviation();

    private StringBuilder sb = new StringBuilder("Percentage Vaccinated:" + "\n");

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> studentList = model.getAddressBook().getPersonList();
        for (String residence : listResidences) {
            SchoolResidence currResidence = new SchoolResidence(residence);
            try {
                float stats = StatsCommandResidence.calculateRatioVaccinated(studentList, currResidence);
                if (Float.isNaN(stats)) {
                    sb.append(PADDING + currResidence + ": No available data" + "\n");
                    continue;
                }
                sb.append(PADDING + String.format(MESSAGE_STATS_SUCCESS, currResidence.toString(), stats * 100) + "\n");
            } catch (Exception e) {
                throw new CommandException(MESSAGE_STATS_FAILURE);
            }
        }
        for (String fac : listFaculties) {
            Faculty currFaculty = new Faculty(fac);
            try {
                float stats = StatsCommandFaculty.calculateRatioVaccinated(studentList, currFaculty);
                if (Float.isNaN(stats)) {
                    sb.append(PADDING + currFaculty + ": No available data" + "\n");
                    continue;
                }
                sb.append(PADDING + String.format(MESSAGE_STATS_SUCCESS, currFaculty, stats * 100) + "\n");
            } catch (Exception e) {
                throw new CommandException(MESSAGE_STATS_FAILURE);
            }
        }
        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsCommandAll); // instanceof handles nulls
    }

}
