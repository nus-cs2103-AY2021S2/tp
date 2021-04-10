package chim.model;

import chim.model.customer.CustomerId;

public class CustomerIdStub extends CustomerId {
    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    private CustomerIdStub(int id) {
        super(id);
    }

    public static CustomerIdStub getNextId() {
        return getNextId(getNextIdValue());
    }

    public static CustomerIdStub getNextId(int id) {
        return new CustomerIdStub(id);
    }
}
