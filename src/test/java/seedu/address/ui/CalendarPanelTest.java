package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.ObservableCalendarDate;

class CalendarPanelTest {
    private final ObservableCalendarDate ocd = new ObservableCalendarDate(LocalDate.now());
    private final CalendarPanel calendar = new CalendarPanel(ocd);

    @Test
    public void update_setDate_viewingDateSet() {
        LocalDate dateToSet = LocalDate.of(2022, 7, 7);
        ocd.set(dateToSet);
        assertEquals(calendar.viewingDate, dateToSet);
    }

    @Test
    public void update_setDateToday_viewingDateSet() {
        LocalDate dateToSet = LocalDate.now();
        ocd.set(dateToSet);
        assertEquals(calendar.viewingDate, dateToSet);
    }

    @Test
    public void update_resetDate_viewingDateReset() {
        ocd.reset();
        assertEquals(calendar.viewingDate, LocalDate.now());
    }
}
