package seedu.address.model.schedule;

/**
 * Contains methods useful for testing SchedulableUtil.
 */
public class SchedulableTestUtil {
    public static boolean checkEquals(Schedulable schedulableOne, Schedulable schedulableTwo) {
        return schedulableOne.getStartLocalDateTime().isEqual(schedulableTwo.getStartLocalDateTime())
                && schedulableOne.getTerminateLocalDateTime().isEqual(schedulableTwo.getTerminateLocalDateTime());
    }
}
