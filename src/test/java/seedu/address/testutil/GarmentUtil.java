package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Garment;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
/**
 * A utility class for Garment.
 */
public class GarmentUtil {

    /**
     * Returns an add command string for adding the {@code garment}.
     */
    public static String getAddCommand(Garment garment) {
        return AddCommand.COMMAND_WORD + " " + getGarmentDetails(garment);
    }

    /**
     * Returns the part of command string for the given {@code garment}'s details.
     */
    public static String getGarmentDetails(Garment garment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + garment.getName().fullName + " ");
        sb.append(PREFIX_SIZE + garment.getSize().value + " ");
        sb.append(PREFIX_COLOUR + garment.getColour().colour + " ");
        sb.append(PREFIX_DRESSCODE + garment.getDressCode().value + " ");
        sb.append(PREFIX_TYPE + garment.getType().value + " ");
        garment.getDescriptions().stream().forEach(
            s -> sb.append(PREFIX_DESCRIPTION + s.descriptionName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditGarmentDescriptor}'s details.
     */
    public static String getEditGarmentDescriptorDetails(EditGarmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSize().ifPresent(size -> sb.append(PREFIX_SIZE).append(size.value).append(" "));
        descriptor.getColour().ifPresent(colour -> sb.append(PREFIX_COLOUR).append(colour.colour).append(" "));
        descriptor.getDressCode().ifPresent(dresscode -> sb.append(PREFIX_DRESSCODE)
                .append(dresscode.value).append(" "));
        descriptor.getType().ifPresent(type -> sb.append(PREFIX_TYPE)
                .append(type.value). append(" "));

        if (descriptor.getDescriptions().isPresent()) {
            Set<Description> descriptions = descriptor.getDescriptions().get();
            if (descriptions.isEmpty()) {
                sb.append(PREFIX_DESCRIPTION);
            } else {
                descriptions.forEach(s -> sb.append(PREFIX_DESCRIPTION).append(s.descriptionName).append(" "));
            }
        }
        return sb.toString();
    }
}
