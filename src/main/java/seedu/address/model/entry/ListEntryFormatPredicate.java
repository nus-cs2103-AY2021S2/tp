package seedu.address.model.entry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public class ListEntryFormatPredicate implements Predicate<Entry> {

    private final String keyword;

    public ListEntryFormatPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Entry entry) {
        LocalDate today = LocalDate.from(LocalDateTime.now());
        LocalDate yesterday = today.plusDays(-1);
        LocalDate lastDay = today.plusDays(7);
        LocalDate startDate = LocalDate.from(entry.getStartDate());
        LocalDate endDate = LocalDate.from(entry.getEndDate());
        if (keyword.equals("day")) {
            return today.equals(startDate) || today.equals(endDate);
        } else if (keyword.equals("week")) {
            return startDate.isAfter(yesterday) && startDate.isBefore(lastDay)
                || endDate.isAfter(yesterday) && endDate.isBefore(lastDay);
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return (other instanceof ListEntryFormatPredicate)
                && ((ListEntryFormatPredicate) other).keyword.equals(keyword);
    }
}
