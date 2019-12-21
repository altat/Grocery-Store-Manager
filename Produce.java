/**
 * @author Tanvir Tatla
 *
 * This class represents a produce item at a grocery store. This class extends
 * GroceryItem.
 */
public class Produce extends GroceryItem {
    private boolean isOrganic;

    Produce(String name, int quantity, double price, boolean isOrganic) {
        super(name, quantity, price);
        this.isOrganic = isOrganic;
    }

    Produce (String name, int quantity){
        super(name, quantity, 0);
    }

    /**
     * Constructor that sets the instance variables using a line from a text
     * file
     * @param inputLine: a line of the input text file
     * @Pre assumes that the line is in format "Type Name Quantity Price
     * isOrganic"
     */
    Produce(String inputLine) {
        String[] parts = inputLine.split(" ");

        setName(parts[1]);
        setQuantity(Integer.parseInt(parts[2]));
        setPrice(Double.parseDouble(parts[3]));
        isOrganic = Boolean.parseBoolean(parts[4]);
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    /**
     * toString - places the item's information into a String that is
     * formatted into columns
     * @return returns a String containing the values of this Produce object's
     * instance variables
     */
    public String toString() {
        String retVal = super.toString();
        return String.format("%-56s%18s", retVal,
                "organic: " + isOrganic);
    }
}
