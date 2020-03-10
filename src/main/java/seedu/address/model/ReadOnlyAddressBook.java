package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.product.Product;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Customer> getPersonList();
    ObservableList<Product> getProductList();

}
