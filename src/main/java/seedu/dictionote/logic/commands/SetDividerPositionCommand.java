package seedu.dictionote.logic.commands;

/**
 * Sets the position of contact divider.
 */
public abstract class SetDividerPositionCommand extends Command {
    public static final double NORMALIZE_RATIO = 10;
    protected final int position;

    /**
     * Creates an SetDividerPositionCommand that set an divider to a the specified position
     */
    public SetDividerPositionCommand(int position) {
        assert position <= 9 || position >= 1 : "Position should be parsed and validate before passing in";
        this.position = position;
    }
}
