package seedu.us.among.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserGuideTest {
    private final UserGuide userGuide = new UserGuide();

    @Test
    void getCommand() {
        assertEquals(userGuide.getCommand(), "");
    }

    @Test
    void setCommand() {
        userGuide.setCommand("Hello");
        assertEquals(userGuide.getCommand(), "Hello");
        userGuide.setCommand("");
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
