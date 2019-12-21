/**
* CSS 143 Final Assignment: Grocery Manager
* Instructor: Lesley Kalmin
*
* Modified by Tanvir Tatla
*
* This class puts the whole assignment together. It keeps stock of a grocery
* store and can add items to a reorder list if the store runs out of that item.
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class GroceryManager {
	ArrayList<GroceryItem> inventory = new ArrayList<>();
	HashSet<String> reorderList = new HashSet<>();

    /**
     * readInventory - reads from a text file and stocks the inventory
     * @throws FileNotFoundException
     * This method was provided for this assignment and therefore was not
     * written by Tanvir Tatla
     */
	public void readInventory() throws FileNotFoundException {
		Scanner input = null;
		try {
			input = new Scanner(new FileInputStream("groceryInventory.txt"));
			// the first line has numbers that correspond to the number of
            // each GroceryItem subclass.
			String qline = input.nextLine();
			String[] qparts = qline.split(" ");
			// the first number is number of dairy items
			int nDairy = Integer.parseInt(qparts[0]);
			// the second number is number of produce items
			int nProduce = Integer.parseInt(qparts[1]);

			int i = 0;

			// while there are more lines in groceryInventory.txt
			while (input.hasNext()) {
				String line = input.nextLine();

				// add the next line to inventory as a Dairy item if i is
                // less than nDairy (the number of lines that start with Dairy)
				if (i++ < nDairy) {
					inventory.add(new Dairy(line));
				}
                // add the next line to inventory as a Produce item if i is
                // less than nProduce (the number of lines that start with
                // Produce)
				else if (i++ < nProduce + nDairy) {
					inventory.add(new Produce(line));
				}
                // add the rest of the lines to inventory as a Meat item
                // since it is the only item type that remains
				else {
					inventory.add(new Meat(line));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			input.close();
		}
	}

	/**
	 * processOrder - subtracts the quantity ordered of a GroceryItem from
     * the quantity of that item in the inventory.
	 * @param order: the GroceryOrder (ArrayList filled with GroceryItems) to
     *               process
	 */
	public void processOrder(GroceryOrder order) {
	    // iterate over each item in the order
		for (int i = 0; i < order.size() - 1; i++) {
		    // sets the quantity of the item in the order and in the inventory
			GroceryItem orderItem = (GroceryItem) order.get(i);
			int quantityOrdered = orderItem.getQuantity();
			GroceryItem item = findItemByName(orderItem.getName());
			try {
                int quantity = item.getQuantity();

                try {
                    // calculate the quantity after the order is processed
                    int newQuantity = quantity - quantityOrdered;

                    // if the new quantity is less than zero, then set the
                    // inventory quantity to zero, add to reorderList and
                    // throw an exception.
                    if (newQuantity <= 0) {
						reorderList.add(item.getName());
						item.setQuantity(0);
						throw new GroceryException("out of " + item.getName() +
                                "  (need " + newQuantity * -1 +
                                " more to complete order).");
					} else // otherwise update the quantity in inventory
                        item.setQuantity(newQuantity);
                } catch (GroceryException e) {
                    // if an exception was caught, then the new quantity is
                    // less than zero. This prints the "out of xxx" message
                    System.out.println(e.getMessage());
                }
            } catch (NullPointerException e) {
			    // if a NullPointerException was caught, this means the item
                // in the order does not exist.
                System.out.println(orderItem.getName() + " not found in " +
                        "inventory.");
            }
		}
    }

    /**
     * findItemByName - searches for an item by name
     * @param name
     * @return returns the object if it exists in the inventory. If not, then
     * returns null.
     */
    public GroceryItem findItemByName(String name) {
        // iterate over the items in the inventory
	    for (int i = 0; i < inventory.size() - 1; i++) {
	        // if the inventory at 'i' equals the target
	        if (name.equals(inventory.get(i).getName()))
	            // then return that target
	            return inventory.get(i);
        }

        //otherwise return null
        return null;
    }

    /**
     * sortByName - sorts the items in inventory by name (starting from A-Z)
     */
    public void sortByName() {
        // iterate over the inventory's size
	    for (int i = 0; i < inventory.size() - 1; i++) {
	        int minIndex = i;

	        // for every item after index 'i'
	        for (int j = i + 1; j < inventory.size(); j++) {
	            // find the index of the item with the smallest name
	            if (inventory.get(j).compareTo(inventory.get(minIndex)) < 0)
	                minIndex = j;
            }

            // swap the item with the smallest name with the item at index 'i'
            GroceryItem temp = inventory.get(minIndex);
	        inventory.set(minIndex, inventory.get(i));
	        inventory.set(i, temp);
        }
    }

    /**
     * sortByPrice - sorts the items in inventory by price from lowest to
     * highest
     */
    public void sortByPrice() {
	    boolean swapped;
	    GroceryItem temp;

	    // for each item in the inventory
	    for (int i = 0; i < inventory.size() - 1; i++) {
	        swapped = false;


	        for (int j = 0; j < inventory.size() - i - 1; j++) {
	            // if the item at 'j' has a greater price than the item to
                // the right of 'j'
	            if (inventory.get(j).getPrice() > inventory.get(j + 1).getPrice()) {
	                // swap 'j' and 'j + 1'
	                temp = inventory.get(j);
	                inventory.set(j, inventory.get(j + 1));
	                inventory.set(j + 1, temp);
	                swapped = true;
                }
            }

            // if no items were swapped in the inner loop, then terminate
            // this outer loop
            if (!swapped) break;
        }
    }

    /**
     * displayInventory - prints each item in the inventory
     */
    public void displayInventory() {
        // iterate over the items in inventory
	    for (GroceryItem item : inventory) {
	        System.out.println(item);
        }
    }

    /**
     * getReorderList - getter for reorderList
     * @return returns reorderList
     */
    public HashSet<String> getReorderList() {
        // iterate over the items in inventory
	    for (GroceryItem item : inventory) {
	        // if the quantity of the item is zero, then add it to reorderList
	        if (item.getQuantity() == 0) {
                reorderList.add(item.getName());
            }
        }

        return reorderList;
    }
}
