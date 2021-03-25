package seedu.partyplanet.logic.commands;

import java.util.List;

import seedu.partyplanet.model.Model;

/**
 * Toggles the theme of PartyPlanet between Dark and Pink.
 */
public class ToggleThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String MESSAGE_SUCCESS = "Changed to %s theme!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles PartyPlanet's theme between Dark and Pink.";
    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD;
    private static boolean isDark = false;

    private static final List<String> pinkTheme = List.of("view/PinkPastelTheme.css", "view/ExtensionsPinkPastel.css");
    private static final List<String> darkTheme = List.of("view/DarkTheme.css", "view/Extensions.css");

    @Override
    public CommandResult execute(Model model) {
        CommandResult commandResult;
        if (isDark) {
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "Pink"), false, pinkTheme);
        } else {
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "Dark"), false, darkTheme);
        }

        isDark = !isDark;
        return commandResult;
    }
}
