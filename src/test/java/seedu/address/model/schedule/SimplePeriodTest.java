package seedu.address.model.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SimplePeriodTest {
    private static LocalDateTime startTimeOne =
            LocalDateTime.of(2021,2,2,5,30,0);
    private static LocalDateTime endTimeOne =
            LocalDateTime.of(2021,2,2,5,40,0);
    private static LocalDateTime startTimeTwo
            = LocalDateTime.of(2020,2,2,5,40,0);
    private static LocalDateTime endTimeTwo
            = LocalDateTime.of(2020,2,2,6,0,0);
    private static LocalDateTime startTimeThree
            = LocalDateTime.of(2020,2,2,5,59,0);
    private static LocalDateTime endTimeThree
            = LocalDateTime.of(2020,2,2,6,10,0);

    @Test
    public void isConflict_nonOverlapExceptAtEndPoint_noConflict() {
        SimplePeriod simplePeriodOne = new SimplePeriod("one", startTimeOne, endTimeOne);
        SimplePeriod simplePeriodTwo = new SimplePeriod("two", startTimeTwo, endTimeTwo);
        assertFalse(simplePeriodOne.isConflict(simplePeriodTwo));
    }

    @Test
    public void isConflict_overlapAtMoreThanEndPoint_hasConflict() {
        SimplePeriod simplePeriodTwo = new SimplePeriod("one", startTimeTwo, endTimeTwo);
        SimplePeriod simplePeriodThree = new SimplePeriod("two", startTimeThree, endTimeThree);
        assertTrue(simplePeriodTwo.isConflict(simplePeriodThree));
    }

    @Test
    public void isConflict_noOverlap_noConflict() {
        SimplePeriod simplePeriodOne = new SimplePeriod("one", startTimeOne, endTimeOne);
        SimplePeriod simplePeriodThree = new SimplePeriod("two", startTimeThree, endTimeThree);
        assertFalse(simplePeriodOne.isConflict(simplePeriodThree));
    }

}
