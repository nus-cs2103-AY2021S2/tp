package seedu.booking.logic.commands;

import java.util.EnumMap;

/**
 * Represents what what to show after a command execution.
 */
public enum CommandShowType {
    COMMAND_SHOW_NONE,
    COMMAND_SHOW_PERSONS,
    COMMAND_SHOW_VENUES,
    COMMAND_SHOW_BOOKINGS,
    COMMAND_SHOW_PREVIOUS
    ;

    private static final EnumMap<CommandShowType, String> enumMap = new EnumMap<CommandShowType, String>(CommandShowType.class);

    static {
        enumMap.put(CommandShowType.COMMAND_SHOW_NONE, "None");
        enumMap.put(CommandShowType.COMMAND_SHOW_PERSONS, "Persons");
        enumMap.put(CommandShowType.COMMAND_SHOW_VENUES, "Venues");
        enumMap.put(CommandShowType.COMMAND_SHOW_BOOKINGS, "Bookings");
        enumMap.put(CommandShowType.COMMAND_SHOW_PREVIOUS, "Previous");
    }

    @Override
    public String toString() {
        return enumMap.get(this);
    }
}
