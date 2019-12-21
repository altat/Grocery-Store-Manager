/**
 * @author Tanvir Tatla
 *
 * This class inherits from Exception.
 */
public class GroceryException extends Exception {
    /**
     * Constructor with a pre-made message
     */
    public GroceryException() {
        super("Item is not found");
    }

    /**
     * Constructor that takes a message
     */
    public GroceryException(String message) {
        super(message);
    }
}
