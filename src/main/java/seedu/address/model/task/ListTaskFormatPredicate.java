package seedu.address.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ListTaskFormatPredicate implements Predicate<Task> {

    private final String keyword;

    public ListTaskFormatPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Task task) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.plusDays(-1);
        LocalDate lastday = today.plusDays(7);
        if (keyword.equals("day")) {
            return today.equals(task.getDate());
        } else {
            return task.getDate().isAfter(yesterday) && task.getDate().isBefore(lastday);
        }
    }
}
