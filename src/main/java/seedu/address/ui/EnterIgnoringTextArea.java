package seedu.address.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*
* Custom TextArea that disables going to a new line when Enter button is pressed.
* Author: jewelsea
* https://gist.github.com/jewelsea/5624145
 */
public class EnterIgnoringTextArea extends TextArea {
    final TextArea myTextArea = this;

    EnterIgnoringTextArea() {
        addEventFilter(KeyEvent.KEY_PRESSED, new TabAndEnterHandler());
    }

    class TabAndEnterHandler implements EventHandler<KeyEvent> {
        private KeyEvent recodedEvent;

        @Override public void handle(KeyEvent event) {
            if (recodedEvent != null) {
                recodedEvent = null;
                return;
            }

            Parent parent = myTextArea.getParent();
            if (parent != null) {
                if (event.getCode() == KeyCode.ENTER) {
                    if (event.isControlDown()) {
                        recodedEvent = recodeWithoutControlDown(event);
                        myTextArea.fireEvent(recodedEvent);
                    } else {
                        Event parentEvent = event.copyFor(parent, parent);
                        myTextArea.getParent().fireEvent(parentEvent);
                    }
                    event.consume();
                }
            }
        }

        private KeyEvent recodeWithoutControlDown(KeyEvent event) {
            return new KeyEvent(
                    event.getEventType(),
                    event.getCharacter(),
                    event.getText(),
                    event.getCode(),
                    event.isShiftDown(),
                    false,
                    event.isAltDown(),
                    event.isMetaDown()
            );
        }
    }
}
