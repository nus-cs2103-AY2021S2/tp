package fooddiary.model.tag;

import static java.util.Objects.requireNonNull;

import fooddiary.commons.util.AppUtil;

public class TagSchool extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "School(s) should be of displayed schools.\n"
                                                        + School.listAll();

    private School school;
    private String tag;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid tag name.
     */
    public TagSchool(String tag) {
        super(School.find(tag).name());
        requireNonNull(tag);
        AppUtil.checkArgument(isValidTagName(tag), tag + MESSAGE_CONSTRAINTS);
        if (isValidTagName(tag)) {
            this.school = School.find(tag);
            this.tag = School.find(tag).name();
        }
    }

    public String getTag() {
        return this.tag;
    }

    public static boolean isValidTagName(String tag) {
        return School.matches(tag) && !(tag.equals(School.INVALID.name()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagSchool // instanceof handles nulls
                && this.tag.equals(((Tag) other).tag)); // state check
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + this.tag + ']';
    }
}
