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
        assertFalse(Breed.isValidBreed("123")); // numerical digits
        assertFalse(Breed.isValidBreed("Corgi123")); // alphanumeric input
        assertFalse(Breed.isValidBreed("Corgi 123")); // alphanumeric + spaces

        // valid breed
        assertTrue(Breed.isValidBreed("Poodle"));
        assertTrue(Breed.isValidBreed("German Shepherd"));
    }

    @Test
    public void equals() {
        Breed breed1 = new Breed("Corgi");
        Breed breed2 = new Breed("Shiba Inu");

        // same object -> returns true
        assertTrue(breed1.equals(breed1));

        // same values -> returns true
        Breed breed1Copy = new Breed("Corgi");
        assertTrue(breed1.equals(breed1Copy));

        // different types -> returns false
        assertFalse(breed1.equals(1));

        // null -> returns false
        assertFalse(breed1.equals(null));

        // different breed -> returns false
        assertFalse(breed1.equals(breed2));
    }
}
