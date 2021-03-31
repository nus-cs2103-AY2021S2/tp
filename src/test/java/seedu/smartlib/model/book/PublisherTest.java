package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;

public class PublisherTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Publisher(new Name(null)));
    }

    @Test
    public void constructor_invalidPublisher_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Publisher(new Name("")));
    }

    @Test
    public void isValidPublisher() {
        // null publisher
        assertThrows(NullPointerException.class, () -> Publisher.isValidPublisher(null));

        // invalid publisher
        assertFalse(Publisher.isValidPublisher("")); // empty string
        assertFalse(Publisher.isValidPublisher(" ")); // spaces only
        assertFalse(Publisher.isValidPublisher("北京出版社")); // non-English publisher name
        assertFalse(Publisher.isValidPublisher("Bloomsbury.Children")); // publisher name with special characters
        assertFalse(Publisher.isValidPublisher(".")); // publisher name with characters only
        assertFalse(Publisher.isValidPublisher(" Bloomsbury")); // publisher name starting with space


        // valid publisher
        assertTrue(Publisher.isValidPublisher("Penguin Random House")); // publisher name with space
        assertTrue(Publisher.isValidPublisher("Houghton Mifflin and Harcourt")); // very long publisher name
        assertTrue(Publisher.isValidPublisher("P")); // one character publisher name
        assertTrue(Publisher.isValidPublisher("HarperCollins ")); // publisher name with trailing space
        assertTrue(Publisher.isValidPublisher("Scholastic 2002")); // publisher name with digits

    }

}
