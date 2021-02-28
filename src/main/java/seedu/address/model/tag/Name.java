package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Name.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Name.VALIDATION_REGEX;

public class Name {

    public final String TutorName;
    /**
     * Constructs a {@code Tag}.
     *
     * @param TutorName A valid tutor name tag name.
     */

     public Name(String TutorName) {
         requireNonNull(TutorName);
         checkArgument(isValidName(TutorName), MESSAGE_CONSTRAINTS);
         this.TutorName = TutorName;
        }

    /**
     * Returns true if a given string is a valid tag name.
     */
     public static boolean isValidName(String test) {
            return test.matches(VALIDATION_REGEX);
        }

     @Override
     public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tag.Name // instanceof handles nulls
                && TutorName.equals(((seedu.address.model.tag.Name) other).TutorName)); // state check
     }
}
