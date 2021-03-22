package seedu.budgetbaby.logic.statistics;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.month.Month;

/**
 * Created at the start of every launch of BudgetBaby
 */
public class Statistics {

    private final Month currMonth;
//    private List<String> monthlyCategories;
//    private List<String> pastMonths;
    private BudgetBabyModel model;

    Statistics(BudgetBabyModel model) {
        this.model = model;
        currMonth = model.getCurrentDisplayMonth();
    }

    private boolean withinSixMonths(Month month) {
        YearMonth curr = month.getMonth();
        YearMonth end = currMonth.getMonth();
        YearMonth start = end.minusMonths(6);
        return (curr.equals(end) || curr.equals(start) || (curr.isBefore(end) && curr.isAfter(start)));
    }

    private List<Month> getPastMonths() {
        List<Month> monthList = model.getFilteredMonthList();
        return monthList.stream().filter(this::withinSixMonths).collect(Collectors.toList());
    }

    public String getPastMonthStatistics() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
        StringBuilder s = new StringBuilder();
        for (Month m : getPastMonths()) {
            s.append(String.format("%s: %.2f/%.2f\n", m.getMonth().format(formatter), m.getTotalExpenses(),
                    m.getBudget().getAmount()));
        }
        return s.toString();
    }

}
