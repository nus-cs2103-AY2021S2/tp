package seedu.us.among.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserGuideTest {
    private final UserGuide userGuide = new UserGuide();

    @Test
    void getAction() {
        assertEquals(userGuide.getAction(), "");
    }

    @Test
    void setAction() {
        userGuide.setAction("Hello");
        assertEquals(userGuide.getAction(), "Hello");
        userGuide.setAction("");
    }

    @Test
    void getFormat() {
        assertEquals(userGuide.getFormat(), "");
    }

    @Test
    void setFormat() {
        userGuide.setFormat("Hello");
        assertEquals(userGuide.getFormat(), "Hello");
        userGuide.setFormat("");
    }

    @Test
    void getExample() {
        assertEquals(userGuide.getExample(), "");
    }

    @Test
    void setExample() {
        userGuide.setExample("Hello");
        assertEquals(userGuide.getExample(), "Hello");
        userGuide.setExample("");
    }
}
