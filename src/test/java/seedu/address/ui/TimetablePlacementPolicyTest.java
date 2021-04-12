package seedu.address.ui;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.schedule.Schedulable;
import seedu.address.model.schedule.SchedulableTestUtil;
import seedu.address.model.schedule.SimplePeriod;




public class TimetablePlacementPolicyTest {
    private static LocalDate localDate = LocalDate.of(2021, 3, 3);
    private static TimetablePlacementPolicy timetablePlacementPolicy =
            new TimetablePlacementPolicy(localDate);

    //===================isWithinRange ==========================================================
    @Test
    public void isWithinRange_dateOutOfRangeAfterEnd() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 10, 7, 0),
                LocalDateTime.of(2021, 3, 10, 8, 0)
        );
        assertFalse(timetablePlacementPolicy.isWithinRange(schedulable));
    }

    @Test
    public void isWithinRange_dateOutOfRangeBeforeStart() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 3, 6, 0),
                LocalDateTime.of(2021, 3, 3, 7, 0, 0)
        );
        assertFalse(timetablePlacementPolicy.isWithinRange(schedulable));
    }

    @Test
    public void isWithinRange_overlapWithStart_dateInRange() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 3, 6, 0),
                LocalDateTime.of(2021, 3, 3, 7, 0, 1)
        );
        assertTrue(timetablePlacementPolicy.isWithinRange(schedulable));
    }

    @Test
    public void isWithinRange_overlapWithEnd_dateInRange() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 10, 6, 59, 59),
                LocalDateTime.of(2021, 3, 10, 8, 0)
        );
        assertTrue(timetablePlacementPolicy.isWithinRange(schedulable));
    }

    //================ Get Column ==================================================================

    @Test
    public void getColumn_firstDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 3, 7, 0),
                LocalDateTime.of(2021, 3, 3, 8, 0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.ONE);
    }

    @Test
    public void getColumn_fourthDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 6, 7, 0),
                LocalDateTime.of(2021, 3, 6, 8, 0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.FOUR);
    }


    @Test
    public void getColumn_fifthDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 7, 7, 0),
                LocalDateTime.of(2021, 3, 7, 8, 0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.FIVE);
    }

    // Boundary Value Analysis
    @Test
    public void getColumn_seventhDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 10, 6, 59, 59),
                LocalDateTime.of(2021, 3, 10, 7, 0)
        );
        assertTrue(timetablePlacementPolicy.getColumnPlacement(schedulable) == TimetableView.Column.SEVEN);
    }

    //====================== Get Vertical Position =====================================================================

    @Test
    public void getVerticalPosition_zero() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 3, 7, 0, 0),
                LocalDateTime.of(2021, 3, 3, 13, 0)
        );
        assertEquals(timetablePlacementPolicy.getVerticalPosition(schedulable), 0.0);
    }
    @Test
    public void getVerticalPosition_middleOfTHeDay() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 3, 19, 0, 0),
                LocalDateTime.of(2021, 3, 3, 21, 0)
        );
        assertEquals(timetablePlacementPolicy.getVerticalPosition(schedulable),
                (double) TimetablePlacementPolicy.TIMETABLE_DISPLAY_SIZE / 2);
    }

    // different column (Boundary Value analysis)
    @Test
    public void getVerticalPosition_startOfTHeDayDifferentColumn() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 4, 7, 0, 0),
                LocalDateTime.of(2021, 3, 4, 8, 0)
        );
        assertEquals(timetablePlacementPolicy.getVerticalPosition(schedulable), 0.0);
    }

    //======================= getLengthOfSlot ===========================================

    @Test
    public void getLengthOfSlot_twohours() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 4, 7, 0, 0),
                LocalDateTime.of(2021, 3, 4, 9, 0)
        );
        double actualLength = (double) 2.0 / 24 * TimetablePlacementPolicy.TIMETABLE_DISPLAY_SIZE;
        assertEquals(timetablePlacementPolicy.getLengthOfSlot(schedulable), actualLength);
    }

    @Test
    public void getLengthOfSlot_ofLengthDayMinusOneSecond() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 4, 7, 0, 0),
                LocalDate.of(2021, 3, 5).atTime(LocalTime.MAX).minusHours(17)
        );
        //numberOfSecondsInADay = 86400.0;
        double actualLength =
                (double) 86399.0 / 86400 * TimetablePlacementPolicy.TIMETABLE_DISPLAY_SIZE;
        assertEquals(timetablePlacementPolicy.getLengthOfSlot(schedulable), actualLength);
    }

    //==========================Break Into Day Units ===========================================

    //test if the schedule is broken and filtered out.
    @Test
    public void breakIntoDayUnits_overlappingOutOfRange() {
        Schedulable schedulable = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 8, 10 , 0, 0),
                LocalDateTime.of(2021, 3, 11, 8, 0, 0)
        );
        Schedulable partOne = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 8, 10 , 0, 0),
                LocalDate.of(2021, 3, 9).atTime(LocalTime.MAX).minusHours(17)
        );
        Schedulable partTwo = new SimplePeriod(
                "this",
                LocalDateTime.of(2021, 3, 9, 7 , 0, 0),
                LocalDate.of(2021, 3, 10).atTime(LocalTime.MAX).minusHours(17)
        );

        List<? extends Schedulable> filteredProcessedList =
                timetablePlacementPolicy.breakIntoDayUnits(schedulable).collect(Collectors.toList());
        assertTrue(SchedulableTestUtil.checkEquals(filteredProcessedList, List.of(partOne, partTwo)));

    }









}
