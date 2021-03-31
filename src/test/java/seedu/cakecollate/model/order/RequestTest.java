package seedu.cakecollate.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RequestTest {

    @Test
    public void equals() {
        Request request = new Request("Hello");

        // same object -> returns true
        assertTrue(request.equals(request));

        // same values -> returns true
        Request requestCopy = new Request(request.value);
        assertTrue(request.equals(requestCopy));

        // different types -> returns false
        assertFalse(request.equals(1));

        // null -> returns false
        assertFalse(request.equals(null));

        // different request -> returns false
        Request differentRequest = new Request("Bye");
        assertFalse(request.equals(differentRequest));
    }
}

