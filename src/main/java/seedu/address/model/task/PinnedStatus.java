package seedu.address.model.task;

/**
 * Represents the pinned status of a task in SOChedule.
 * Guarantees: the status is either PINNED or UNPINNED.
 */
public class PinnedStatus implements Comparable<PinnedStatus> {
    public static final String VALIDATION_REGEX = "PINNED|UNPINNED";
    public static final String MESSAGE_CONSTRAINTS =
            "Pinned Statuses should only either by \"PINNED\" or \"UNPINNED\"";

    private Status pinnedStatus;

    enum Status {
        PINNED,
        UNPINNED
    }

    /**
     * Constructs an {@code PinnedStatus}.
     */
    public PinnedStatus() {
        pinnedStatus = Status.UNPINNED;
    }

    /**
     * Constructs an {@code PinnedStatus}.
     *
     * @param cs A valid pinned status ("PINNED" or "UNPINNED").
     */
    public PinnedStatus(String cs) {
        switch (cs) {
        case "PINNED":
            pinnedStatus = Status.PINNED;
            break;

        case "UNPINNED":
            pinnedStatus = Status.UNPINNED;
            break;

        default:
            // should not reach here since validation is pre-done.
            break;
        }
    }

    public boolean isPinned() {
        return pinnedStatus == Status.PINNED;
    }

    /**
     * Flips the pinned status.
     */
    public void flipPinnedStatus() {
        if (pinnedStatus == Status.PINNED) {
            pinnedStatus = Status.UNPINNED;
        } else {
            pinnedStatus = Status.PINNED;
        }
    }

    /**
     * Returns true if a given string is a valid pinned status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Updates the pinned status of the task to PINNED;
     * Does nothing if already pinned
     */
    public void pin() {
        pinnedStatus = Status.PINNED;
    }

    /**
     * Updates the pinned status of the task to UNPINNED;
     * Does nothing if already unpinned
     */
    public void unpin() {
        pinnedStatus = Status.UNPINNED;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PinnedStatus // instanceof handles nulls
                && pinnedStatus.equals(((PinnedStatus) other).pinnedStatus)); // state check
    }

    @Override
    public int hashCode() {
        return pinnedStatus.hashCode();
    }

    /**
     * Returns a String representation of {@code PinnedStatus}.
     */
    public String toString() {
        if (pinnedStatus == Status.PINNED) {
            return "Pinned";
        } else {
            return "Unpinned";
        }
    }

    @Override
    public int compareTo(PinnedStatus other) {
        if (isPinned() && !other.isPinned()) {
            return 1;
        } else if (!isPinned() && other.isPinned()) {
            return -1;
        } else {
            return 0;
        }
    }
}
