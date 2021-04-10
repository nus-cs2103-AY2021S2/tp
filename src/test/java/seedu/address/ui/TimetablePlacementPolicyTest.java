package seedu.address.ui;

import org.junit.jupiter.api.Test;
import seedu.address.model.schedule.Schedulable;
import seedu.address.model.schedule.SimplePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TimetablePlacementPolicyTest {
    private static LocalDate localDate = LocalDate.of(2021,3,3);
    private static TimetablePlacementPolicy timetablePlacementPolicy =
            new TimetablePlacementPolicy(localDate);

    @Test
    public void isWithinRange_dateOutOfRange() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 10, 7, 0),
                LocalDateTime.of(2021,3, 10,8,0)
        );
        assertFalse(timetablePlacementPolicy.isWithinRange(schedulable));
    }
    @Test
    public void isWithinRange_dateInRange() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 10, 6, 59, 59),
                LocalDateTime.of(2021,3, 10,8,0)
        );
        assertTrue(timetablePlacementPolicy.isWithinRange(schedulable));
    }

    @Test
    public void getColumn_firstDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 3, 7, 0),
                LocalDateTime.of(2021, 3, 3,8,0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.ONE);
    }

    @Test
    public void getColumn_fourthDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 6, 7, 0),
                LocalDateTime.of(2021,3, 6,8,0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.FOUR);
    }


    @Test
    public void getColumn_fifthDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 7, 7, 0),
                LocalDateTime.of(2021,3, 7,8,0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.FIVE);
    }

    // Boundary Value Analysis
    @Test
    public void getColumn_seventhDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 10, 6, 59, 59),
                LocalDateTime.of(2021,3, 10,7,0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.SEVEN);
    }



}
