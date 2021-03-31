package dog.pawbook.logic.parser;

import dog.pawbook.logic.commands.DeleteCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public abstract class DeleteCommandParser<T extends DeleteCommand> implements Parser<T> {
    private final Class<T> cls;

    public DeleteCommandParser(Class<T> cls) {
        this.cls = cls;
    }

    protected abstract String getUsageText();

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public T parse(String userInput) throws ParseException {
        try {
            Integer id = Integer.parseInt(userInput.trim());
            try {
                return cls.getDeclaredConstructor(Integer.class).newInstance(id);
            } catch (Exception e) {
                throw new AssertionError("All DeleteCommand child classes should have Integer constructor!");
            }
        } catch (NumberFormatException e) {
            throw new ParseException(getUsageText(), e);
        }
    }
}
