/**
 * @author Tanvir Tatla
 *
 * This class represents a Meat item at a grocery store. This class extends
 * GroceryItem.
 */
public class Meat extends GroceryItem {
    boolean isGround;

    Meat(String name, int quantity, double price, boolean isGround) {
        super(name, quantity, price);
        this.isGround = isGround;
    }

    Meat (String name, int quantity) {
        super(name, quantity, 0);
    }

    /**
     * Constructor that sets the instance variables using a line from a text
     * file
     * @param inputLine: a line of the input text file
     * @Pre assumes that the line is in format "Type Name Quantity Price
     * isGround"
     */
    Meat(String inputLine) {
        String[] parts = inputLine.split(" ");

        setName(parts[1]);
        setQuantity(Integer.parseInt(parts[2]));
        setPrice(Double.parseDouble(parts[3]));
        isGround = Boolean.parseBoolean(parts[4]);
    }

    public boolean isGround() {
        return isGround;
    }

    public void setGround(boolean ground) {
        isGround = ground;
    }

    /**
     * toString - places the item's information into a String that is
     * formatted into columns
     * @return returns a String containing the values of this Meat object's
     * instance variables
     */
    public String toString() {
        String retVal = super.toString();
        return String.format("%-56s%17s", retVal,"ground; " + isGround);
    }
}
