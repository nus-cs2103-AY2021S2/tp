package seedu.address.model.diet;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DietPlanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DietPlan(null, "", null,
                null));
        assertThrows(NullPointerException.class, () -> new DietPlan("", null, null,
                null));
        assertThrows(NullPointerException.class, () -> new DietPlan("", "", null,
                null));
    }

}
