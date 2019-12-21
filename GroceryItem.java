/**
 * @author Tanvir Tatla
 *
 * This class represents an item that you can purchase at a grocery store.
 */
public abstract class GroceryItem implements Comparable {
    private String name;
    private int quantity;
    private double price;

    /**
     * 3 parameter Constructor for GroceryItem
     * @param name: the name of the item
     * @param quantity: the quantity of the item
     * @param price: the price of the item
     */
    public GroceryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public GroceryItem(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * toString - places the item's information into a String that is
     * formatted into columns
     * @return returns a String containing the values of GroceryItem's
     * instance variables
     */
    public String toString() {
        return String.format("%-26s%-17s%-6s$%.2f", "Name: " + name,
                "Quantity: " + quantity, "Price: ", price);
    }

    /**
     * compareTo - compares the name of the GroceryItems
     * @param o: the other GroceryItem to compare this with
     * @return returns 0 if they have the same name, a positive integer if
     * this has a greater name, and a negative integer otherwise
     */
    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof GroceryItem)) return 1;

        GroceryItem that = (GroceryItem) o;

        return this.name.compareTo(that.name);
    }
}
