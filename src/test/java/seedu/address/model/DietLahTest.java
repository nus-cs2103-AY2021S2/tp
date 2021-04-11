package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDietLah.getTypicalDietLah;

import org.junit.jupiter.api.Test;

import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.User;

public class DietLahTest {

    private final DietLah dietLah = new DietLah();

    @Test
    public void constructor() {
        assertFalse(dietLah.getUser() == null);
        assertFalse(dietLah.getFoodIntakeList() == null);
        assertFalse(dietLah.getFoodList() == null);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dietLah.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDietLah_replacesData() {
        DietLah newData = getTypicalDietLah();
        dietLah.resetData(newData);
        assertEquals(newData, dietLah);
    }

    /**
     * A stub ReadOnlyDietLah whose persons list can violate interface constraints.
     */
    private static class DietLahStub implements ReadOnlyDietLah {

        DietLahStub() {
        }

        @Override
        public UniqueFoodList getFoodList() {
            // TODO: Improve abstraction
            return null;
        }

        @Override
        public User getUser() {
            return null;
        }

        @Override
        public FoodIntakeList getFoodIntakeList() {
            return null;
        }

    }

}
