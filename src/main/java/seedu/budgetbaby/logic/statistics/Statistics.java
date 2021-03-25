package seedu.budgetbaby.logic.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.FinancialRecord;

public class Statistics {

    private final ObservableList<Month> monthList;
    private BudgetBabyModel model;

    /**
     * Instantiates the Statistics object by taking in a BudgetBabyModel during initialisation.
     * @param model BudgetBabyModel object.
     */
    public Statistics(BudgetBabyModel model) {
        this.model = model;
        monthList = model.getFilteredMonthList();
    }

    //    /**
    //     * Checks if a {@code Month} falls within the past 6 months of the currently displayed month.
    //     * @param month {@code Month} that is being checked.
    //     * @return True if it falls within the past 6 months, and False otherwise.
    //     */
    //    private boolean withinSixMonths(Month month) {
    //        assert month != null;
    //
    //        YearMonth curr = month.getMonth();
    //        YearMonth end = currMonth.getMonth();
    //        YearMonth start = end.minusMonths(6);
    //        return (curr.equals(end) || curr.equals(start) || (curr.isBefore(end) && curr.isAfter(start)));
    //    }

    private List<Month> getPastMonths() {
        List<Month> monthList = model.getFilteredMonthList();
        return monthList.stream().limit(6).collect(Collectors.toList());
        //        return monthList.stream().filter(this::withinSixMonths).collect(Collectors.toList());
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

    private List<CategoryStatistics> allCategories() {
        assert monthList.size() == 1;
        Month currMonth = monthList.get(0);

        ObservableList<FinancialRecord> list = currMonth.getFinancialRecordList();
        HashMap<Category, CategoryStatistics> map = new HashMap<>();
        for (FinancialRecord fr : list) {
            for (Category c : fr.getTags()) {
                if (map.containsKey(c)) {
                    map.put(c, new CategoryStatistics(c, map.get(c).getAmount() + fr.getAmount().getValue()));
                } else {
                    map.put(c, new CategoryStatistics(c, fr.getAmount().getValue()));
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Obtains top 5 categories which the user spends on
     */
    public List<CategoryStatistics> getTopCategories() {
        List<CategoryStatistics> list = allCategories();
        Collections.sort(list);
        return list.stream().limit(5).collect(Collectors.toList());
    }
}
