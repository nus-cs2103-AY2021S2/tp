package seedu.address.model.schedule;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Contains methods useful for testing SchedulableUtil.
 */
public class SchedulableTestUtil {
    public static final Comparator<Schedulable> COMPARE_BY_START_END_THEN_NAME = (x, y) -> {
        if (!x.getStartLocalDateTime().isEqual(y.getStartLocalDateTime())) {
            return x.getStartLocalDateTime().compareTo(y.getStartLocalDateTime());
        }
        if (!x.getTerminateLocalDateTime().isEqual(y.getTerminateLocalDateTime())) {
            return x.getTerminateLocalDateTime().compareTo(y.getTerminateLocalDateTime());
        }
        return x.getNameString().compareTo(y.getNameString());
    };
    /**
     * check whether the start/end times of a schedulable are equal to the start/end times of another.
     * @param schedulableOne
     * @param schedulableTwo
     * @return
     */
    public static boolean checkEquals(Schedulable schedulableOne, Schedulable schedulableTwo) {
        return schedulableOne.getStartLocalDateTime().isEqual(schedulableTwo.getStartLocalDateTime())
                && schedulableOne.getTerminateLocalDateTime().isEqual(schedulableTwo.getTerminateLocalDateTime())
                && schedulableOne.getNameString().equals(schedulableTwo.getNameString());
    }
    /**
     * Checks if a list of Schedulables  are equal to another list. See
     * {@link #checkEquals(Schedulable,Schedulable)} for the condition of equality between two schedulables. Two lists
     * are considered equal if the collection of Schedulables inside are equal.
     */
    public static boolean checkEquals(List<? extends Schedulable> listOne, List<? extends Schedulable> listTwo) {
        if (listOne.size() != listTwo.size()) {
            return false;
        }

        List<? extends Schedulable> listOneSorted = new ArrayList<>(listOne);
        listOneSorted.sort(COMPARE_BY_START_END_THEN_NAME);
        List<? extends Schedulable> listTwoSorted = new ArrayList<>(listTwo);
        listTwoSorted.sort(COMPARE_BY_START_END_THEN_NAME);
        for (int i = 0; i < listOne.size(); i++) {
            if (!checkEquals(listOneSorted.get(0), listTwoSorted.get(0))) {
                return false;
            }
        }
        return true;
    }

}
