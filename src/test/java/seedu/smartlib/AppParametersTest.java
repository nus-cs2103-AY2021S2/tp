package seedu.smartlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.application.Application;

public class AppParametersTest {

    private final ParametersStub parametersStub = new ParametersStub();
    private final AppParameters expected = new AppParameters();

    @Test
    public void parse_validConfigPath_success() {
        parametersStub.namedParameters.put("config", "config.json");
        expected.setConfigPath(Paths.get("config.json"));
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_nullConfigPath_success() {
        parametersStub.namedParameters.put("config", null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_invalidConfigPath_success() {
        parametersStub.namedParameters.put("config", "a\0");
        expected.setConfigPath(null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void equals() {
        AppParameters ap = new AppParameters();
        Path configPath = Paths.get("config.json");
        Path configPath2 = Paths.get("configuration.json");

        // EP: same object
        assertTrue(ap.equals(ap));

        // EP: null
        assertFalse(ap.equals(null));

        // EP: different data types
        assertFalse(ap.equals(5.0f));
        assertFalse(ap.equals(" "));

        // EP: same values
        assertTrue(ap.equals(expected));
        ap.setConfigPath(configPath);
        expected.setConfigPath(configPath);
        assertTrue(ap.equals(expected));

        // EP: different values
        ap.setConfigPath(configPath2);
        assertFalse(ap.equals(expected));
    }

    @Test
    public void hashCodeTest() {
        AppParameters ap = new AppParameters();
        Path configPath = Paths.get("config.json");
        Path configPath2 = Paths.get("configuration.json");
        ap.setConfigPath(configPath);
        int hashcode = ap.hashCode();

        // same object, same hashcode
        assertEquals(hashcode, ap.hashCode());

        // different object, same path -> same hashcode
        expected.setConfigPath(configPath);
        assertEquals(hashcode, expected.hashCode());

        // different object, different path -> different hashcode
        expected.setConfigPath(configPath2);
        assertNotEquals(hashcode, expected.hashCode());
    }

    private static class ParametersStub extends Application.Parameters {

        private Map<String, String> namedParameters = new HashMap<>();

        @Override
        public List<String> getRaw() {
            throw new AssertionError("should not be called");
        }

        @Override
        public List<String> getUnnamed() {
            throw new AssertionError("should not be called");
        }

        @Override
        public Map<String, String> getNamed() {
            return Collections.unmodifiableMap(namedParameters);
        }

    }

}
