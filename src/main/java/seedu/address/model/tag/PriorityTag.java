package seedu.address.model.tag;

/**
 * Represents a priority tag in the task
 *
 */

public class PriorityTag {

    private State state;
    private String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public PriorityTag(String tagName) {
        if (tagName.equals("LOW")) {
            this.state = state.LOW;
            this.tagName = tagName;
        } else if (tagName.equals("MEDIUM")) {
            this.state = state.MEDIUM;
            this.tagName = tagName;
        } else if (tagName.equals("HIGH")) {
            this.state = state.HIGH;
            this.tagName = tagName;
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

}
