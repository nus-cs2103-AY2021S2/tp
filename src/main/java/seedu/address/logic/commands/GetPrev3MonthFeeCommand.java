package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Lists all emails in the address book to the user, delimited by semi colon.
 */
public class GetPrev3MonthFeeCommand extends Command {
    public static final String COMMAND_WORD = "3Month_Fee";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the total expected monthly fee"
        + "for this month + previous 2 months";

    /**
     * Get the local date time format of the month and year combined
     * @return LocalDateTime of the month and year combined
     */
    private LocalDateTime getLocalDate(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }

    /**
     * Gets the total fee between 2 time period {@code currMonthYear and nextMonthYear}
     * @param studentList List of students
     * @param startPeriod Start of time period
     * @param endPeriod End of time period
     * @return Total fee between the 2 time period
     */
    private double getFee(List<Student> studentList, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        double fee = 0;
        for (Student student : studentList) {
            for (Session session : student.getListOfSessions()) {
                LocalDateTime dateTime = session.getSessionDate().getDateTime();
                if (dateTime.compareTo(startPeriod) >= 0 && dateTime.compareTo(endPeriod) < 0) {
                    // This session date is within the period
                    fee += session.getFee().getFee();
                }
            }
        }
        return fee;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getFilteredStudentList();

        LocalDateTime currMonthYear;
        LocalDateTime nextMonthYear;
        StringBuilder resultString = new StringBuilder("Total Monthly fee for past 3 months is:");

        LocalDateTime now = LocalDateTime.now();
        Month month = new Month(now.getMonth().getValue());
        Year year = new Year(now.getYear());
        currMonthYear = getLocalDate(month, year);
        nextMonthYear = currMonthYear.plusMonths(1);

        for (int i = 0; i < 3; i ++) {
            // Get current month value and add the result to the resulting string
            double currMonthlyFee = getFee(studentList, currMonthYear, nextMonthYear);
            resultString.append(String.format("\n%s, %d: $%.2f", currMonthYear.getMonth().name(),
                currMonthYear.getYear(), currMonthlyFee));

            // Update to previous month
            currMonthYear = currMonthYear.minusMonths(1);
            nextMonthYear = currMonthYear.plusMonths(1);
        }

        return new CommandResult(resultString.toString());
    }
}
