package seedu.address.ui;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class LabelWithIconTest {
    private final String WRONG_IMG_PATH = "notExist.png";

    @Test
    public void constructor_nullIconImgPath_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new LabelWithIcon(null, ""));
    }

    @Test
    public void constructor_wrongIconImgPath_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new LabelWithIcon(WRONG_IMG_PATH, ""));
    }
}
