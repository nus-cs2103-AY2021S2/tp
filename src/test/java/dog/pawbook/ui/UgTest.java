package dog.pawbook.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UgTest {
    private final UG userGuide = new UG();

    @Test
    void getAction() {
        assertEquals(userGuide.getAction(), "");
    }

    @Test
    void setAction() {
        userGuide.setAction("testAction");
        assertEquals(userGuide.getAction(), "testAction");
        userGuide.setAction("");
    }

    @Test
    void getFormat() {
        assertEquals(userGuide.getFormat(), "");
    }

    @Test
    void setFormat() {
        userGuide.setFormat("test123");
        assertEquals(userGuide.getFormat(), "test123");
        userGuide.setFormat("");
    }
}
