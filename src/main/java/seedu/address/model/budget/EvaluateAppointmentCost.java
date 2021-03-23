package seedu.address.model.budget;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class EvaluateAppointmentCost {

    ReadOnlyAddressBook addressBook;
    ReadOnlyAppointmentBook appointmentBook;

    //TODO implement GetAddressBook GetAppointmentBook GetNameRate class (HashMap)

    public static void main(String[] args) {
        EvaluateAppointmentCost trial = new EvaluateAppointmentCost();
    }

    public EvaluateAppointmentCost() {
        try {
            Path path = Path.of("data/addressbook.json");
            JsonAddressBookStorage jsonAddressBookStorage =
                    new JsonAddressBookStorage(path);

            Optional<ReadOnlyAddressBook> optionalAddressBook =
                    jsonAddressBookStorage.readAddressBook(path);

            this.addressBook =
                    optionalAddressBook.orElseGet(SampleDataUtil::getSampleAddressBook);

        } catch (Exception e) {
            this.addressBook = SampleDataUtil.getSampleAddressBook();
        }

        System.out.println(addressBook.getPersonList().size());
        for (Person person : SampleDataUtil.getSamplePersons()) {
            System.out.println(person);
        }
    }
}
