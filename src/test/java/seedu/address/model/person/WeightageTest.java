package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightageTest {

    @Test
    public void constructor_null_throwsNullPointerExcpetion() {
        assertThrows(NullPointerException.class, () -> new Weightage(null));
    }

    @Test
    public void constructor_invalidWeightage_throwsIllegalArgumentException() {
        Integer invalidWeightageLowerBound = -1;
        Integer invalidWeightageUpperBound = 101;
        assertThrows(IllegalArgumentException.class, () -> new Weightage(invalidWeightageLowerBound));
        assertThrows(IllegalArgumentException.class, () -> new Weightage(invalidWeightageUpperBound));
    }

    @Test
    public void isValidWeightage() {
        // null weightage
        assertThrows(NullPointerException.class, () -> Weightage.isValidWeightage(null));

        // invalid weightage
        assertFalse(Weightage.isValidWeightage(-1)); // negative value
        assertFalse(Weightage.isValidWeightage(101)); // value that is greater than 100

        // valid weightage
        assertTrue(Weightage.isValidWeightage(0)); // 0 (lower boundary)
        assertTrue(Weightage.isValidWeightage(100)); // 100 (upper boundary)
        assertTrue(Weightage.isValidWeightage(50)); // typical value

    }
}
