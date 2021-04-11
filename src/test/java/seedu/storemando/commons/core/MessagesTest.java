package seedu.storemando.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MessagesTest {

    @Test
    public void unknownCommandMessage_correctString() {
        String message = "Unknown command.";
        assertEquals(message, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void invalidCommandFormatMessage_correctString() {
        String message = "Invalid command format! \n%1$s";
        assertEquals(message, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void invalidItemDisplayedIndexMessage_correctString() {
        String message = "The item index provided is invalid.";
        assertEquals(message, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void moreThanOneItemListedMessage_correctString() {
        String message = "%1$d items listed!";
        assertEquals(message, Messages.MESSAGE_MORE_THAN_ONE_ITEM_LISTED_OVERVIEW);
    }

    @Test
    public void lessThanTwoItemsMessage_correctString() {
        String message = "%1$d item listed!";
        assertEquals(message, Messages.MESSAGE_LESS_THAN_TWO_ITEMS_LISTED_OVERVIEW);
    }
}
