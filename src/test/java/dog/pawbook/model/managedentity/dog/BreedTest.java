package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BreedTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Breed(null));
    }

    @Test
    public void constructor_invalidBreed_throwsIllegalArgumentException() {
        String invalidBreed = "";
        assertThrows(IllegalArgumentException.class, () -> new Breed(invalidBreed));
    }

    @Test
    public void isValidBreed() {
        // null breed
        assertThrows(NullPointerException.class, () -> Breed.isValidBreed(null));

        // invalid breed
        assertFalse(Breed.isValidBreed("")); // empty string
        assertFalse(Breed.isValidBreed(" ")); // spaces only

        // valid breed
        assertTrue(Breed.isValidBreed("Poodle"));
        assertTrue(Breed.isValidBreed("German Shepherd"));
    }
}
