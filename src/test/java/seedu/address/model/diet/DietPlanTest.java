package seedu.address.model.diet;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class DietPlanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DietPlan(null, "", null));
        assertThrows(NullPointerException.class, () -> new DietPlan("", null, null));
        assertThrows(NullPointerException.class, () -> new DietPlan("", "", null));
    }

}
