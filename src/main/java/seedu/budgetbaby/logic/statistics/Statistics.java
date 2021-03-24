package seedu.budgetbaby.logic.statistics;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.month.Month;

public class Statistics {

    private final Month currMonth;
    private BudgetBabyModel model;

    /**
     * Instantiates the Statistics object by taking in a BudgetBabyModel during initialisation.
     * @param model BudgetBabyModel object.
     */
    public Statistics(BudgetBabyModel model) {
        this.model = model;
        currMonth = model.getFilteredMonthList().get(0);
    }

    /**
     * Checks if a {@code Month} falls within the past 6 months of the currently displayed month.
     * @param month {@code Month} that is being checked.
     * @return True if it falls within the past 6 months, and False otherwise.
     */
    private boolean withinSixMonths(Month month) {
        assert month != null;

        YearMonth curr = month.getMonth();
        YearMonth end = currMonth.getMonth();
        YearMonth start = end.minusMonths(6);
        return (curr.equals(end) || curr.equals(start) || (curr.isBefore(end) && curr.isAfter(start)));
    }

    private List<Month> getPastMonths() {
        List<Month> monthList = model.getFilteredMonthList();
        return monthList.stream().filter(this::withinSixMonths).collect(Collectors.toList());
    }

    /**
     * Obtains statistics of past 6 {@code Month}s as a list and returns it.
     * @return List of MonthStatistics of past 6 months.
     */
    public List<MonthStatistics> getPastMonthStatistics() {
        List<MonthStatistics> list = new ArrayList<>();
        for (Month m : getPastMonths()) {
            list.add(new MonthStatistics(m));
        }
        return list;
    }

}
