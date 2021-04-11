package dog.pawbook.logic.parser;

import static java.util.Objects.requireNonNull;

import dog.pawbook.logic.commands.DeleteCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Entity;

public abstract class DeleteCommandParser<T extends DeleteCommand<? extends Entity>> implements Parser<T> {
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
        requireNonNull(userInput);
        String trimmedInput = userInput.trim();
        if (trimmedInput.isEmpty() || !trimmedInput.matches("\\d+")) {
            throw new ParseException(getUsageText());
        }

        int id = ParserUtil.parseId(trimmedInput);
        try {
            return cls.getDeclaredConstructor(Integer.class).newInstance(id);
        } catch (Exception e) {
            throw new AssertionError("All DeleteCommand child classes should have Integer constructor!");
        }
    }
}
