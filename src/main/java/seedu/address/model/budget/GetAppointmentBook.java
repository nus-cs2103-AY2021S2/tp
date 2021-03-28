package seedu.address.model.budget;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonAppointmentBookStorage;

/**
 * Class to retrieve appointment book solely from file path.
 */
public class GetAppointmentBook {

    private ReadOnlyAppointmentBook appointmentBook;

    /**
     * Primary constructor.
     * @param relativeFilePath Relative file path in String.
     */
    public GetAppointmentBook(String relativeFilePath) {
        try {
            Path path = Path.of(relativeFilePath);
            JsonAppointmentBookStorage jsonAppointmentBookStorage =
                    new JsonAppointmentBookStorage(path);

            Optional<ReadOnlyAppointmentBook> optionalAppointmentBook =
                    jsonAppointmentBookStorage.readAppointmentBook(path);
            this.appointmentBook =
                    optionalAppointmentBook.orElseGet(SampleDataUtil::getSampleAppointmentBook);
        } catch (Exception e) {
            this.appointmentBook = SampleDataUtil.getSampleAppointmentBook();
        }
    }

    /**
     * Getter method that retrieves the appointment book.
     * @return Read only appointment book.
     */
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return appointmentBook;
    }

}
