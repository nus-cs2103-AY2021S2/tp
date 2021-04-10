package seedu.address.model.tag;

/**
 * Represents a priority tag in the task
 *
 */

public class PriorityTag {

    public static final String MESSAGE_CONSTRAINTS = "PriorityTag should be a LOW/MEDIUM/HIGH";
    public static final String MESSAGE_INVALID_INPUT = "Invalid Input, setting priority tag to default LOW";

    private State state;
    private String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public PriorityTag(String tagName) {

        boolean isValidTag = validateTag(tagName);

        if (isValidTag) {
            setState(tagName);
        } else {
            this.state = state.LOW;
            this.tagName = "LOW";
        }

    }

    /**
     * method to return state of ptag
     * @return state of the ptag
     */

    public State getState() {
        return this.state;
    }

    /**
     * method to return the state of the ptag in String value
     * @return String value of the ptag
     */

    public String getTagName() {
        return this.tagName;
    }

    /**
     * method to return the priority value of the priority tag
     * @return int value of priority tag
     */

    public int getPriority() {
        return this.state.getPriorityValue();
    }

    /**
     * method to validate if is valid tagName
     * @param tagName String value of tagName to validate
     * @return boolean value to validated
     */

    public boolean validateTag(String tagName) {
        if (tagName.equals("LOW") || tagName.equals("MEDIUM") || tagName.equals("HIGH")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method to init state for priorityTag
     * @param tagName String value of priorityTag
     */

    private void setState(String tagName) {
        if (tagName.equals("LOW")) {
            this.state = state.LOW;
            this.tagName = tagName;
        } else if (tagName.equals("MEDIUM")) {
            this.state = state.MEDIUM;
            this.tagName = tagName;
        } else {
            this.state = state.HIGH;
            this.tagName = tagName;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityTag // instanceof handles nulls
                && tagName.equals(((PriorityTag) other).tagName)); // state check
    }


}
