package dog.pawbook.testutil;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOB;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import dog.pawbook.logic.commands.AddDogCommand;
import dog.pawbook.model.managedentity.dog.Dog;

/**
 * A utility class for Dog.
 */
public class DogUtil {

    /**
     * Returns an add command string for adding the {@code dog}.
     */
    public static String getAddCommand(Dog dog) {
        return AddDogCommand.COMMAND_WORD + " " + Dog.ENTITY_WORD + " " + getDogDetails(dog);
    }

    /**
     * Returns the part of command string for the given {@code dog}'s details.
     */
    public static String getDogDetails(Dog dog) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + dog.getName().fullName + " ");
        sb.append(PREFIX_BREED + dog.getBreed().value + " ");
        sb.append(PREFIX_DOB + dog.getDob().value + " ");
        sb.append(PREFIX_SEX + dog.getSex().value + " ");
        sb.append(PREFIX_OWNERID + Integer.toString(dog.getOwnerId()) + " ");
        dog.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
