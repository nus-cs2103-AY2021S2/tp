package seedu.partyplanet.logic.commands;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.ui.Theme;

/**
 * Toggles the theme of PartyPlanet between Dark and Pink.
 */
public class ToggleThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String MESSAGE_SUCCESS = "Changed to %s theme!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles PartyPlanet's theme between Dark and Pink.";
    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD;
    private static Theme theme = null;

    @Override
    public CommandResult execute(Model model) {
        CommandResult commandResult;

        // Initialize starting theme
        if (theme == null) {
            theme = model.getGuiSettings().getTheme();
        }

        // Switch themes
        switch (theme) {
        default:
        case DARK:
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "Pink"), false, Theme.PASTEL);
            theme = Theme.PASTEL;
            break;
        case PASTEL:
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "Dark"), false, Theme.DARK);
            theme = Theme.DARK;
            break;
        }
        return commandResult;
    }
}
