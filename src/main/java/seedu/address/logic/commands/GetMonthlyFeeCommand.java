package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;
import seedu.address.model.session.Session;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Lists all emails in the address book to the user, delimited by semi colon.
 */
public class GetMonthlyFeeCommand extends Command {
    public static final String COMMAND_WORD = "fee";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the monthly fee of a student in a "
        + "particular month and year"
        + "\nParameters: "
        + PREFIX_NAME + "STUDENT NAME "
        + PREFIX_MONTH + "MONTH "
        + PREFIX_YEAR + "YEAR "
        + "\nExample: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_MONTH + "1 "
        + PREFIX_YEAR + "2021 ";

    private Name studentName;
    private Month month;
    private Year year;

    /**
     * Creates an GetMonthlyFeeCommand to get the specified monthly fee
     */
    public GetMonthlyFeeCommand(Name studentName, Month month, Year year) {
        requireAllNonNull(studentName, month, year);
        this.studentName = studentName;
        this.month = month;
        this.year = year;
    }

    public LocalDateTime getNextMonth(Month month, Year year) {
        Month nextMonth;
        Year nextYear;
        int currMonth = month.getMonth();

        if (currMonth < 12) {
            nextMonth = new Month(currMonth + 1);
            return getLocalDate(nextMonth, year);
        } else {
            // currMonth = 12 because currMonth can only be from 1 to 12
            nextMonth = new Month(1);
            nextYear = new Year(year.getYear() + 1);
            return getLocalDate(nextMonth, nextYear);
        }
    }

    /**
     * Get the local date time format of the month and year combined
     * @return LocalDateTime of the month and year combined
     */
    public static LocalDateTime getLocalDate(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasName(studentName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_NAME);
        }

        Student student = model.getStudentWithName(studentName);
        LocalDateTime currMonthYear;
        LocalDateTime nextMonthYear;
        double monthlyFee = 0;

        currMonthYear = getLocalDate(month, year);
        nextMonthYear = getNextMonth(month, year);

        for (Session session : student.getListOfSessions()) {
            LocalDateTime dateTime = session.getSessionDate().getDateTime();
            if (dateTime.compareTo(currMonthYear) >= 0 && dateTime.compareTo(nextMonthYear) < 0) {
                // This session date is within the current month
                monthlyFee += session.getFee().getFee();
            }
        }

        return new CommandResult(String.format("Monthly fee for %s on %s, %s is $%.2f",
            studentName.toString(), month.getMonthName(), year.toString(), monthlyFee));
    }
}
