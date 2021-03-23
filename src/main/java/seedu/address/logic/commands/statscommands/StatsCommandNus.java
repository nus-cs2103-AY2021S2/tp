package seedu.address.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class StatsCommandNus extends StatsCommand {
    public static final String MESSAGE_STATS_SUCCESS = "Percentage NUS vaccinated: %.2f%%";
    public static final String MESSAGE_STATS_FAILURE = "No available data in Vax@NUS";

    @Override
    public CommandResult execute(Model model) throws CommandException { // NUS
        requireNonNull(model);
        List<Student> studentList = model.getStudentBook().getStudentList();
        int totalStudents = studentList.size();
        int counter = 0;

        try {
            for (Student p : studentList) {
                if (p.isVaccinated()) {
                    counter++;
                }
            }
            float stats = (float) counter / totalStudents;
            if (Float.isNaN(stats) || stats > 1) {
                throw new CommandException(MESSAGE_STATS_FAILURE);
            }
            return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, stats * 100));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsCommandNus); // instanceof handles nulls
    }
}
