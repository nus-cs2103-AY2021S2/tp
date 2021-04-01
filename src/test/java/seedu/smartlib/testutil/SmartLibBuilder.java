package seedu.smartlib.testutil;

import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.reader.Reader;

/**
 * A utility class to help with building SmartLib objects.
 * Example usage: <br>
 *     {@code SmartLib sl = new SmartLibBuilder().withReader("John", "Doe").build();}
 */
public class SmartLibBuilder {

    private SmartLib smartLib;

    /**
     * Creates a {@code SmartLibBuilder} with the default details.
     */
    public SmartLibBuilder() {
        smartLib = new SmartLib();
    }

    /**
     * Initializes the SmartLib with the data of {@code smartLib}.
     *
     * @param smartLib a SmartLib object containing data which we want to copy from.
     */
    public SmartLibBuilder(SmartLib smartLib) {
        this.smartLib = smartLib;
    }

    /**
     * Adds a new {@code Reader} to the {@code SmartLib} that we are building.
     *
     * @param reader reader associated with the SmartLib that we are building.
     * @return a SmartLibBuilder object with the updated reader.
     */
    public SmartLibBuilder withReader(Reader reader) {
        smartLib.addReader(reader);
        return this;
    }

    /**
     * Builds a SmartLib object with the given values set in the smartLib.
     *
     * @return a SmartLib object with the given smartLib.
     */
    public SmartLib build() {
        return smartLib;
    }

}
