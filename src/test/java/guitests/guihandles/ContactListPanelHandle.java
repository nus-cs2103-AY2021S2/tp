package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.contact.Contact;

/**
 * @@author {se-edu}-reused
 * Reused with modification from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * Provides a handle for {@code ContactListPanel} containing the list of {@code ContactCard}.
 */
public class ContactListPanelHandle extends NodeHandle<Node> {
    public static final String CONTACT_LIST_VIEW_ID = "#contactListView";
    private static final String CARD_PANE_ID = "#cardPane";

    private ListView<Contact> listView;
    private Optional<Contact> lastRememberedSelectedContactCard;

    /**
     * Creates a {@code ContactListPanelHandle} with the given {@code contactListPanelNode}.
     *
     * @param contactListPanelNode the {@code Node} to create the {@code ContactListPanelHandle}.
     */
    public ContactListPanelHandle(Node contactListPanelNode) {
        super(contactListPanelNode);
        listView = getChildNode(CONTACT_LIST_VIEW_ID);
    }

    /**
     * Returns a handle to the selected {@code ContactCardHandle}.
     * A maximum of 1 item can be selected at any time.
     *
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public ContactCardHandle getHandleToSelectedCard() {
        List<Contact> selectedContactList = listView.getSelectionModel().getSelectedItems();

        if (selectedContactList.size() != 1) {
            throw new AssertionError("Contact list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(ContactCardHandle::new)
                .filter(handle -> handle.equals(selectedContactList.get(0)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return listView.getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<Contact> selectedCardsList = listView.getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code Contact}s.
     */
    public void navigateToCard(Contact contact) {
        if (!listView.getItems().contains(contact)) {
            throw new IllegalArgumentException("Contact does not exist.");
        }

        guiRobot.interact(() -> {
            listView.scrollTo(contact);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}es.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= listView.getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            listView.scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code ContactCard} at {@code index} in the list.
     */
    public void select(int index) {
        listView.getSelectionModel().select(index);
    }

    /**
     * Returns the {@code ContactCard} handle of a {@code Contact} associated with the {@code index} in the list.
     *
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public ContactCardHandle getContactCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(ContactCardHandle::new)
                .filter(handle -> handle.equals(getContact(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Contact getContact(int index) {
        return listView.getItems().get(index);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Remembers the selected {@code ContactCard} in the list.
     */
    public void rememberSelectedContactCard() {
        List<Contact> selectedItems = listView.getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedContactCard = Optional.empty();
        } else {
            lastRememberedSelectedContactCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code ContactCard} is different from the value remembered by the most recent
     * {@code rememberSelectedContactCard()} call.
     */
    public boolean isSelectedContactCardChanged() {
        List<Contact> selectedItems = listView.getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedContactCard.isPresent();
        } else {
            return !lastRememberedSelectedContactCard.isPresent()
                    || !lastRememberedSelectedContactCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return listView.getItems().size();
    }
}
