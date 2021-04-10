package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;

import seedu.address.commons.core.AddressBookSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path appointmentBookFilePath = Paths.get("data", "appointmentbook.json");
    private AddressBookSettings addressBookSettings = new AddressBookSettings();

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setAppointmentBookFilePath(newUserPrefs.getAppointmentBookFilePath());
        setAddressBookSettings(newUserPrefs.getAddressBookSettings());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public String getTheme() {
        return guiSettings.getTheme();
    }

    public void setTheme(String theme) {
        guiSettings.setTheme(theme);
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getAppointmentBookFilePath() {
        return appointmentBookFilePath;
    }

    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        this.appointmentBookFilePath = appointmentBookFilePath;
    }

    public AddressBookSettings getAddressBookSettings() {
        return addressBookSettings;
    }

    public void setAddressBookSettings(AddressBookSettings addressBookSettings) {
        requireNonNull(addressBookSettings);
        this.addressBookSettings = addressBookSettings;
    }

    public Comparator<Contact> getAddressBookComparator() {
        return addressBookSettings.getComparator();
    }

    public void setAddressBookComparator(String comparator) {
        addressBookSettings.setComparator(comparator);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && appointmentBookFilePath.equals(o.appointmentBookFilePath)
                && addressBookSettings.equals(o.addressBookSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, appointmentBookFilePath,
                addressBookSettings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal address book file location : " + addressBookFilePath);
        sb.append("\nLocal appointment book file location: " + appointmentBookFilePath);
        sb.append("\nAddress Book settings : " + addressBookSettings);
        return sb.toString();
    }

}
