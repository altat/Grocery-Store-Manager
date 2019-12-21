/**
 * @author Tanvir Tatla
 *
 * This class represents a Dairy item at a grocery store. This class extends
 * GroceryItem.
 */
public class Dairy extends GroceryItem {
    private int refrigerationTemperature;

    /**
     * 4 parameter Constructor for Dairy
     * @param name
     * @param quantity
     * @param price
     * @param refrigerationTemperature: the temperature of the refrigerator
     *                                that this item was stored in
     */
    Dairy(String name, int quantity, double price,
          int refrigerationTemperature) {
        super(name, quantity, price);
        this.refrigerationTemperature = refrigerationTemperature;
    }

    Dairy(String name, int quantity) {
        super(name, quantity, 0);
    }

    /**
     * Constructor that sets the instance variables using a line from a text
     * file
     * @param inputLine: a line of the input text file
     * @Pre assumes that the line is in format "Type Name Quantity Price Temp"
     */
    Dairy(String inputLine) {
        String[] parts = inputLine.split(" ");

        setName(parts[1]);
        setQuantity(Integer.parseInt(parts[2]));
        setPrice(Double.parseDouble(parts[3]));
        refrigerationTemperature = Integer.parseInt(parts[4]);
    }

    public int getRefrigerationTemperature() {
        return refrigerationTemperature;
    }

    public void setRefrigerationTemperature(int refrigerationTemperature) {
        this.refrigerationTemperature = refrigerationTemperature;
    }

    /**
     * toString - places the item's information into a String that is
     * formatted into columns
     * @return returns a String containing the values of this Dairy object's
     * instance variables
     */
    public String toString() {
        String retVal = super.toString();
        return String.format("%-56s%20s", retVal,
                "temperature: " + refrigerationTemperature);
    }
}
