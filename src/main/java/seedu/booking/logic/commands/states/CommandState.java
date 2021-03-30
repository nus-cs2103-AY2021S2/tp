package seedu.booking.logic.commands.states;

/**
 * This class keeps track of whether the program is responding to a multi-step command
 */
public class CommandState {

    private boolean isActive;
    private String state;
    private String nextPromptMessage;

    /**
     * Initialises a command state
     */
    public CommandState() {
        this.isActive = false;
        this.state = null;
        this.nextPromptMessage = null;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive() {
        this.isActive = true;
    }

    public void setInactive() {
        this.isActive = false;
        this.state = null;
        this.nextPromptMessage = null;
    }

    public String getState() {
        return this.state;
    }

    public String getNextPromptMessage() {
        return this.nextPromptMessage;
    }

    public void setNextPromptMessage(String message) {
        this.nextPromptMessage = message;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNextState() {}

    public void processInput(Object value) {}

    public Object create() {
        return null;
    }
}
