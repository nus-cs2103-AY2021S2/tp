package seedu.address.model.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public class ListScheduleFormatPredicate implements Predicate<Schedule> {

    private final String keyword;

    public ListScheduleFormatPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Schedule schedule) {
        LocalDate today = LocalDate.from(LocalDateTime.now());
        LocalDate yesterday = today.plusDays(-1);
        LocalDate lastday = today.plusDays(7);
        LocalDate startDate = LocalDate.from(schedule.getStartDate());
        if (keyword.equals("day")) {
            return today.equals(startDate);
        } else {
            return startDate.isAfter(yesterday) && startDate.isBefore(lastday);
        }
    }
}
