package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a priority tag in the task
 *
 */

public class PriorityTag {
    private PriorityTagStatus status;
    public final String label;
    public static final String MESSAGE_CONSTRAINTS = "Priority Tags names should be low, medium or high";

    /**
     * Enum class for the 3 priority types
     *
     */
    public enum PriorityTagStatus {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Constructs a priority tag
     *
     * @param label A valid priority type
     */

    public PriorityTag(String label) {
        //checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.label = label;
        if (label == "low") {
            this.status = PriorityTagStatus.LOW;
        } else if (label == "medium") {
            this.status = PriorityTagStatus.MEDIUM;
        } else if (label == "high") {
            this.status = PriorityTagStatus.HIGH;
        } else {
            this.status = PriorityTagStatus.LOW; // by default
        }
    }

    /**
     * Method to return enum class as string
     * @return a priority tag string
     */

    private String getEnum() {

        if (status == PriorityTagStatus.LOW) {
            return "LOW";
        } else if (status == PriorityTagStatus.MEDIUM) {
            return "MEDIUM";
        } else if (status == PriorityTagStatus.HIGH) {
            return "HIGH";
        } else {
            return "NA";
        }
    }

    /**
     * Method to return priority tag as string
     * @return correct format of enum class
     */
    public String toString() {
        return "[" + getEnum() + "]";
    }

}
