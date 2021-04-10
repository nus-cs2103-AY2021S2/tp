package seedu.smartlib.testutil;

import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.model.reader.Reader;

/**
 * A utility class for Reader.
 */
public class ReaderUtil {

    /**
     * Returns an add command string for adding the {@code reader}.
     *
     * @param reader reader to be added.
     * @return an add command string for adding the reader.
     */
    public static String getAddCommand(Reader reader) {
        return AddReaderCommand.COMMAND_WORD + " " + getReaderDetails(reader);
    }

    /**
     * Returns the part of command string for the given {@code reader}'s details.
     *
     * @param reader reader to be examined.
     * @return details of the reader that is examined.
     */
    public static String getReaderDetails(Reader reader) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_READER + reader.getName().toString() + " ");
        sb.append(PREFIX_PHONE + reader.getPhone().toString() + " ");
        sb.append(PREFIX_EMAIL + reader.getEmail().toString() + " ");
        sb.append(PREFIX_ADDRESS + reader.getAddress().toString() + " ");
        reader.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.getTagName() + " ")
        );
        return sb.toString();
    }

}
