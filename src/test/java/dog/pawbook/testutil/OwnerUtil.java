package dog.pawbook.testutil;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import dog.pawbook.logic.commands.AddOwnerCommand;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * A utility class for Owner.
 */
public class OwnerUtil {

    /**
     * Returns an add command string for adding the {@code owner}.
     */
    public static String getAddCommand(Owner owner) {
        return AddOwnerCommand.COMMAND_WORD + " " + Owner.ENTITY_WORD + " " + getOwnerDetails(owner);
    }

    /**
     * Returns the part of command string for the given {@code owner}'s details.
     */
    public static String getOwnerDetails(Owner owner) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + owner.getName().fullName + " ");
        sb.append(PREFIX_PHONE + owner.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + owner.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + owner.getAddress().value + " ");
        owner.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
