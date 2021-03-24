package seedu.iscam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.iscam.testutil.Assert.assertThrows;
import static seedu.iscam.testutil.TypicalClients.ALICE;
import static seedu.iscam.testutil.TypicalClients.getTypicalLocationBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.exceptions.DuplicateClientException;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.testutil.ClientBuilder;

public class ClientBookTest {

    private final ClientBook clientBook = new ClientBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clientBook.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ClientBook newData = getTypicalLocationBook();
        clientBook.resetData(newData);
        assertEquals(newData, clientBook);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        ClientBookStub newData = new ClientBookStub(newClients);

        assertThrows(DuplicateClientException.class, () -> clientBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientBook.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(clientBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        clientBook.addClient(ALICE);
        assertTrue(clientBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        clientBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(clientBook.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clientBook.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyClientBook whose clients list can violate interface constraints.
     */
    private static class ClientBookStub implements ReadOnlyClientBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        ClientBookStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }
    }

}
