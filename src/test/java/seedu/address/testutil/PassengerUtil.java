package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPassengerDescriptor;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Passenger.
 */
public class PassengerUtil {

    //todo remove STUB declaration
    private static final Price STUB_VALID_PRICE = new Price("1.69");

    /**
     * Returns an add command string for adding the {@code passenger}.
     */
    public static String getAddCommand(Passenger passenger) {
        return AddCommand.COMMAND_WORD + " " + getPassengerDetails(passenger);
    }

    /**
     * Returns the part of command string for the given {@code passenger}'s details.
     */
    public static String getPassengerDetails(Passenger passenger) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + passenger.getName().fullName + " ");
        sb.append(PREFIX_PHONE + passenger.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + passenger.getAddress().value + " ");
        sb.append(PREFIX_TRIPDAY + passenger.getTripDay().value + " ");
        sb.append(PREFIX_TRIPTIME + passenger.getTripTime().value + " ");
        //todo remove STUB price usage
        sb.append(PREFIX_PRICE + STUB_VALID_PRICE.value + " ");

        passenger.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPassengerDescriptor}'s details.
     */
    public static String getEditPassengerDescriptorDetails(EditPassengerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
