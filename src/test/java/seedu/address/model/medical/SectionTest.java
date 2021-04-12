package seedu.address.model.medical;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SectionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Section(null));
        assertThrows(NullPointerException.class, () -> new Section(null, null));
        assertThrows(NullPointerException.class, () -> new Section("Title", null));
        assertThrows(NullPointerException.class, () -> new Section(null, "Body"));
    }
}
