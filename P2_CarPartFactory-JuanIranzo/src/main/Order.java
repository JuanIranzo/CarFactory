package main;

import interfaces.Map;

/**@author Juan Iranzo <juan.iranzo@upr.edu>
 * This class represents an object called 'Order', and defines its parameters and behaviors
 * 
**/
public class Order {
   private int id;
   private String customerName;
   private Map<Integer,Integer> requestedParts;
   private boolean fulfilled;
   
   
   /**This is the constructor for the Order object
	 * 
	 * @param 'id, 'customerName', 'requestedParts', 'fulfilled'. This constructor receives an integer value
	 * called 'id' which represents the identifier for the Order, a String 'customerName' which represents the name of the customer.
	 * The constructor also receives a Map called 'requestedParts' which holds as key value an Integer which represents the id of the part requested
	 * and as value, another integer which represents the number of parts requested. Finally it receives a boolean variable 'fulfilled', which sets the order's fulfilled status.
	**/
   
    public Order(int id, String customerName, Map<Integer, Integer> requestedParts, boolean fulfilled) {
        this.id=id;
        this.customerName=customerName;
        this.requestedParts=requestedParts;
        this.fulfilled=fulfilled;
    }
    
	/**Returns the current order's ID parameter
	 * 
	 * @param empty
	 * @return int id, returns the id of the order as an integer
	 **/
    
    public int getId() {
        return this.id;
    }
    
    /**Assigns a specific id to current Order or new Order 
     * 
   	 * @param id, receives a integer value "id"
   	 * @return void
   	**/
    
    public void setId(int id) {
        this.id=id;
    }
    
	/**Returns the current order's fulfilled parameter
	 * 
	 * @param empty
	 * @return boolean isFulfilled, returns the fulfilled status of the order as a boolean
	**/
    
    public boolean isFulfilled() {
      return this.fulfilled;
    }
    
    /**Assigns a fulfilled value to current Order or new Order 
     * 
   	 * @param fulfilled, receives a boolean value "fulfilled"
   	 * @return void
   	**/
    
    public void setFulfilled(boolean fulfilled) {
        this.fulfilled=fulfilled;
    }
    
	/**Returns the current order's requestedParts parameter
	 * 
	 * @param empty
	 * @return Map<Integer,Integer> requestedParts, returns the requestedParts of the order as a Map holding <Integer,Integer>
	**/
    
    public Map<Integer, Integer> getRequestedParts() {
       return this.requestedParts;
    }
    
    /**Assigns a specific requested parts to current Order or new Order 
     * 
   	 * @param requestedParts, receives a Map holding integer values "requestedParts"
   	 * @return void
   	**/
    
    public void setRequestedParts(Map<Integer, Integer> requestedParts) {
       this.requestedParts=requestedParts;
    }
    
	/**Returns the current order's customerName parameter
	 * 
	 * @param empty
	 * @return String customerName, returns the name of the customer of the order as an String
	**/
    
    public String getCustomerName() {
      return this.customerName;
    }
    
    /**Assigns a specific customer name to current Order or new Order 
     * 
   	 * @param customerName, receives a String value "customerName"
   	 * @return void
   	**/
    public void setCustomerName(String customerName) {
        this.customerName=customerName;
    }
    /**
     * Returns the order's information in the following format: {id} {customer name} {number of parts requested} {isFulfilled}
     */
    @Override
    public String toString() {
        return String.format("%d %s %d %s", this.getId(), this.getCustomerName(), this.getRequestedParts().size(), (this.isFulfilled())? "FULFILLED": "PENDING");
    }

    
    
}
