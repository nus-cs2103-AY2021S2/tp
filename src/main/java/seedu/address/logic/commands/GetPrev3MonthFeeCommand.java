package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;

/**
 * Gets current month and previous 2 months expected monthly fee based on the current list of sessions
 */
public class GetPrev3MonthFeeCommand extends Command {
    public static final String COMMAND_WORD = "3_month_fees";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the totalled fees per month, "
        + "for this month + previous 2 months";

    /**
     * Gets the local date time format of the month and year combined
     * @return LocalDateTime of the month and year combined
     */
    private LocalDateTime getLocalDate(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        LocalDateTime currMonthYear;
        LocalDateTime nextMonthYear;
        StringBuilder resultString = new StringBuilder("Total Monthly fee for past 3 months is:");

        LocalDateTime now = LocalDateTime.now();
        Month month = new Month(now.getMonth().getValue());
        Year year = new Year(now.getYear());
        currMonthYear = getLocalDate(month, year);
        nextMonthYear = currMonthYear.plusMonths(1);

        for (int i = 0; i < 3; i++) {
            // Get current month value and add the result to the resulting string
            double currMonthlyFee = model.getFee(currMonthYear, nextMonthYear);
            resultString.append(String.format("\n%s, %d: $%.2f", currMonthYear.getMonth().name(),
                currMonthYear.getYear(), currMonthlyFee));

            // Update to previous month
            currMonthYear = currMonthYear.minusMonths(1);
            nextMonthYear = currMonthYear.plusMonths(1);
        }

        return new CommandResult(resultString.toString());
    }
}
