package seedu.booking.logic;

import seedu.booking.logic.commands.states.CommandState;

public class StatefulLogicManager {
    private static CommandState commandState = new CommandState();

    /**
     * Resets the static {@code CommandState} stored in StatefulLogicManager with a new given {@code CommandState}.
     */
    public static void setCommandState(CommandState commandState) {
        StatefulLogicManager.commandState = commandState;
    }

    public static boolean isStateActive() {
        return StatefulLogicManager.commandState.isActive();
    }

    public static void setStateActive() {
        StatefulLogicManager.commandState.setActive();
    }

    public static void setStateInactive() {
        StatefulLogicManager.commandState.setInactive();
    }

    public static String getState() {
        return StatefulLogicManager.commandState.getState();
    }

    public static String getNextPromptMessage() {
        return StatefulLogicManager.commandState.getNextPromptMessage();
    }

    public static void setState(String state) {
        StatefulLogicManager.commandState.setState(state);
    }

    public static void processStateInput(Object value) {
        StatefulLogicManager.commandState.processInput(value);
    }

    public static void setNextState() {
        StatefulLogicManager.commandState.setNextState();
    }

    public static Object create() {
        return StatefulLogicManager.commandState.create();
    }

    public static void resetCommandState() {
        StatefulLogicManager.commandState = new CommandState();
    }
}
