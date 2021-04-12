package dog.pawbook.testutil;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOB;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import dog.pawbook.logic.commands.AddDogCommand;
import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.tag.Tag;

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
        sb.append(PREFIX_NAME).append(dog.getName().fullName).append(" ");
        sb.append(PREFIX_BREED).append(dog.getBreed().value).append(" ");
        sb.append(PREFIX_DOB).append(dog.getDob().value).append(" ");
        sb.append(PREFIX_SEX).append(dog.getSex().value).append(" ");
        sb.append(PREFIX_OWNERID).append(Integer.toString(dog.getOwnerId())).append(" ");
        dog.getTags().forEach(
            s -> sb.append(PREFIX_TAG).append(s.tagName).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditOwnerDescriptor}'s details.
     */
    public static String getEditDogDescriptorDetails(EditDogDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getBreed().ifPresent(breed -> sb.append(PREFIX_BREED).append(breed.value).append(" "));
        descriptor.getDob().ifPresent(dob -> sb.append(PREFIX_DOB).append(dob.value).append(" "));
        descriptor.getSex().ifPresent(sex -> sb.append(PREFIX_SEX).append(sex.value).append(" "));
        descriptor.getOwnerId().ifPresent(id -> sb.append(PREFIX_OWNERID).append(id).append(" "));
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
