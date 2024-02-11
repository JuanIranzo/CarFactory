package main;
/**@author Juan Iranzo <juan.iranzo@upr.edu>
 * This class represents an object called 'CarPart', and defines its parameters and behaviors
 * 
**/
public class CarPart {
    private int id;
    private String name;
    private double weight;
    private boolean isDefective;
    
    /**This is the constructor for the CarPart object
	 * 
	 * @param 'id, 'name', 'weight', 'genre', 'isDefective'. This constructor receives an integer value
	 * called 'id' which represents the identifier for the CarPart, a String 'name' which represents the name of the car part.
	 * The constructor also receives a double value called 'weight' which represents the weight of the part. 
	 *	Finally it receives a boolean variable 'isDefective', which sets the car part's defective status.
	**/
    public CarPart(int id, String name, double weight, boolean isDefective) {
        this.id=id;
        this.name=name;
        this.weight=weight;
        this.isDefective=isDefective;
        
    }
    
	/**Returns the current carPart's ID parameter
	 * 
	 * @param empty
	 * @return int id, returns the id of the CarPart as an integer
	 * 
	**/
    
    public int getId() {
        return this.id;
    }
    
    /**Assigns a specific ID to current CarPart or new CarPart 
     * 
	 * @param id, receives an integer value "id"
	 * @return void
	**/
    
    public void setId(int id) {
        this.id=id;
    }
    
	/**Returns the current carPart's name parameter
	 * 
	 * @param empty
	 * @return String name, returns the name of the car part as a String
	**/
    
    public String getName() {
        return this.name;
    }
    
    /**Assigns a specific name to current CarPart or new CarPart 
     * 
	 * @param name, receives a String value "name"
	 * @return void
	**/
    public void setName(String name) {
        this.name=name;
    }
    
	/**Returns the current car part's weight parameter
	 * 
	 * @param empty
	 * @return double weight, returns the weight of the car part as a double
	**/
    
    public double getWeight() {
      return this.weight;
    }
    
    /**Assigns a specific weight to current CarPart or new CarPart 
     * 
	 * @param weight, receives a double value "weight"
	 * @return void
	**/
    public void setWeight(double weight) {
      this.weight=weight;
    }
    
	/**Returns the current car part's defective parameter
	 * 
	 * @param empty
	 * @return boolean isDefective, returns the defective status of the part as a boolean
	**/
    
    public boolean isDefective() {
      return this.isDefective;
    }
    
    /**Assigns a defective value to current CarPart or new CarPart 
     * 
   	 * @param isDefective, receives a boolean value "isDefective"
   	 * @return void
   	**/
    public void setDefective(boolean isDefective) {
        this.isDefective=isDefective;
    }
    /**Returns the parts name as its string representation
     * 
     * @param empty
     * @return (String) The part name
     */
    public String toString() {
        return this.getName();
    }
}