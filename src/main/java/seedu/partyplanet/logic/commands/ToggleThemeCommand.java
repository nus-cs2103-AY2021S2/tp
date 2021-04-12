package seedu.partyplanet.logic.commands;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.ui.Theme;

/**
 * Toggles the theme of PartyPlanet between Dark and Pastel.
 */
public class ToggleThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String MESSAGE_SUCCESS = "Changed to %s theme!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles PartyPlanet's theme between Dark and Pastel.";
    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        CommandResult commandResult;
        Theme theme = model.getGuiSettings().getTheme();

        switch (theme) {
        case PASTEL:
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "Dark"), false, Theme.DARK);
            break;
        case DARK: // fallthrough
        default:
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, "Pastel"), false, Theme.PASTEL);
            break;
        }
        return commandResult;
    }
}
