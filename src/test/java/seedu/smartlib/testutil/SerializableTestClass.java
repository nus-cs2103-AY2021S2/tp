package seedu.smartlib.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class used to test serialization and deserialization.
 */
public class SerializableTestClass {

    public static final String JSON_STRING_REPRESENTATION = String.format("{%n"
            + "  \"name\" : \"This is a test class\",%n"
            + "  \"listOfLocalDateTimes\" : "
            + "[ \"-999999999-01-01T00:00:00\", \"+999999999-12-31T23:59:59.999999999\", "
            + "\"0001-01-01T01:01:00\" ],%n"
            + "  \"mapOfIntegerToString\" : {%n"
            + "    \"1\" : \"One\",%n"
            + "    \"2\" : \"Two\",%n"
            + "    \"3\" : \"Three\"%n"
            + "  }%n"
            + "}");

    private static final String NAME_TEST_VALUE = "This is a test class";

    private String name;

    private List<LocalDateTime> listOfLocalDateTimes;
    private HashMap<Integer, String> mapOfIntegerToString;

    /**
     * Returns the name test value of this class.
     *
     * @return the name test value of this class.
     */
    public static String getNameTestValue() {
        return NAME_TEST_VALUE;
    }

    /**
     * Returns a list of test values for localDateTime.
     *
     * @return a list of test values for localDateTime.
     */
    public static List<LocalDateTime> getListTestValues() {
        List<LocalDateTime> listOfLocalDateTimes = new ArrayList<>();

        listOfLocalDateTimes.add(LocalDateTime.MIN);
        listOfLocalDateTimes.add(LocalDateTime.MAX);
        listOfLocalDateTimes.add(LocalDateTime.of(1, 1, 1, 1, 1));

        return listOfLocalDateTimes;
    }

    /**
     * Returns a hashmap of test values.
     *
     * @return a hashmap of test values.
     */
    public static HashMap<Integer, String> getHashMapTestValues() {
        HashMap<Integer, String> mapOfIntegerToString = new HashMap<>();

        mapOfIntegerToString.put(1, "One");
        mapOfIntegerToString.put(2, "Two");
        mapOfIntegerToString.put(3, "Three");

        return mapOfIntegerToString;
    }

    /**
     * Updates the test values of this object.
     */
    public void setTestValues() {
        name = getNameTestValue();
        listOfLocalDateTimes = getListTestValues();
        mapOfIntegerToString = getHashMapTestValues();
    }

    /**
     * Returns the name of this object.
     *
     * @return the name of this object.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of localDateTime objects present in this object.
     *
     * @return the list of localDateTime objects present in this object.
     */
    public List<LocalDateTime> getListOfLocalDateTimes() {
        return listOfLocalDateTimes;
    }

    /**
     * Returns the hashmap of test values present in this object.
     *
     * @return the hashmap of test values present in this object.
     */
    public HashMap<Integer, String> getMapOfIntegerToString() {
        return mapOfIntegerToString;
    }

}
