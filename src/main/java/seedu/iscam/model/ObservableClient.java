package seedu.iscam.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import seedu.iscam.model.client.Client;

public class ObservableClient implements ObservableObjectValue<Client> {
    private Client client;
    private List<ChangeListener<? super Client>> listeners = new ArrayList<>();

    public void setClient(Client newClient) {
        Client oldClient = this.client;
        this.client = newClient;
        for (ChangeListener<? super Client> cl: listeners) {
            cl.changed(this, oldClient, newClient);
        }
    }

    @Override
    public Client get() {
        return client;
    }

    @Override
    public void addListener(ChangeListener<? super Client> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Client> listener) {
        this.listeners.remove(listener);
    }

    @Override
    public Client getValue() {
        return client;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
