package main;

import data_structures.HashTableSC;
import data_structures.ArrayList;
import data_structures.LinkedStack;
import data_structures.ListQueue;
import data_structures.BasicHashFunction;
import interfaces.Map;
import interfaces.Entry;
import interfaces.Stack;
import interfaces.List;
import interfaces.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**@author Juan Iranzo <juan.iranzo@upr.edu>
 * This class represents an object called 'CarPartFactory', and defines its parameters and behaviors.
 * The class uses 2 main lists, 'orders' and 'machines'. These lists were implemented using
 * ArrayList because of its efficiency for getting objects and information, even though it is a little
 * less efficient at adding and removing. This implementation was chosen with the idea in mind that most operations
 * that would have to do with those lists would be getting its elements.
 * 
*/
public class CarPartFactory {
	private String orderPath = "input/orders.csv";
	private String partsPath = "input/parts.csv";
	private List<PartMachine> machines = new ArrayList<PartMachine>();
	private List<Order> orders = new ArrayList<Order>();
	private Stack<CarPart> productionBin = new LinkedStack<CarPart>();
	private BasicHashFunction basicHashFunction = new BasicHashFunction();
	private Map<Integer, CarPart> partCatalog = new HashTableSC<Integer, CarPart>(11, basicHashFunction);
	private Map<Integer, List<CarPart>> inventory = new HashTableSC<Integer, List<CarPart>>(11, basicHashFunction);
	private Map<Integer, Integer> defective = new HashTableSC<Integer, Integer>(11, basicHashFunction);

	/**Constructor for the CarPartFactory object
	 * 
	 * Calls the setupMachines, setupOrders, setupInventory, setupProductionBin, and setupDefectives methods
	 * to initialize the parameters of the CarPartFactory
	 * 
	 * @param String orderPath, String partsPath. Receives the file path for the Orders CSV file and the file path
	 * for the Parts CSV files
	**/
	public CarPartFactory(String orderPath, String partsPath) throws IOException {
		this.setupMachines(partsPath);
		this.setupOrders(orderPath);
		this.setupInventory();
		this.setupProductionBin();
		this.setupDefectives();
	}

	/** Returns the current car part factory's 'machines' list parameter
	 * 
	 * @param empty
	 * @return List machines, returns the List of partMachines of the current car
	 * part factory
	 **/

	public List<PartMachine> getMachines() {
		return this.machines;
	}

	/**Assigns a specific List of machines 'machines' to current car part factory or
	 * new car part factory
	 * 
	 * @param List machines, receives a List that holds PartMachine objects
	 * "machines"
	 * @return void
	 **/
	public void setMachines(List<PartMachine> machines) {
		this.machines = machines;
	}

	/** Returns the current car part factory's 'productionBin' stack parameter
	 * 
	 * @param empty
	 * @return Stack productionBin, returns the Stack of car parts of the current
	 * car part factory
	 **/
	public Stack<CarPart> getProductionBin() {
		return this.productionBin;
	}

	/** Assigns a specific Stack of car parts 'production' to current car part
	 * factory or new car part factory
	 * 
	 * @param Stack production, receives a Stack that holds CarPart objects
	 * "production" 
	 * @return void
	 **/
	public void setProductionBin(Stack<CarPart> production) {
		this.productionBin = production;
	}

	/** Returns the current car part factory's 'partCatalog' map parameter
	 * 
	 * @param empty
	 * @return Map partCatalog, returns the Map holding the integer 'id' of the
	 * carParts produced by the factory, and the actual CarPart of the current car
	 * part factory
	 **/

	public Map<Integer, CarPart> getPartCatalog() {
		return this.partCatalog;
	}

	/** Assigns a specific Map holding integer 'id' keys and CarPar object values
	 * "partCatalog" to current car part factory or new car part factory
	 * 
	 * @param Map<Integer,CarPart> partCatalog, receives a Map that holds integer
	 * keys and CarPart objects values "partCatalog"
	 * @return void
	 **/
	public void setPartCatalog(Map<Integer, CarPart> partCatalog) {
		this.partCatalog = partCatalog;
	}

	/** Returns the current car part factory's 'inventory' map parameter
	 * 
	 * @param empty 
	 * @return Map inventory, returns the Map holding the 'id' parameter of the part
	 * and the List holding the actual parts of the current car part factory
	 */
	public Map<Integer, List<CarPart>> getInventory() {
		return this.inventory;
	}

	/** Assigns a specific Map 'inventory' holding integer 'id' keys and a lists of
	 * CarPart objects as values to current car part factory or new car part factory
	 * 
	 * @param Map<Integer, List<CarPart>> 'inventory', receives a Map that holds
	 * integer keys and lists of car parts as values
	 * @return void
	 **/
	public void setInventory(Map<Integer, List<CarPart>> inventory) {
		this.inventory = inventory;
	}

	/** Returns the current car part factory's 'order' list parameter
	 * 
	 * @param empty
	 * @return List orders, returns the List of orders of the current car part
	 * factory
	 **/
	public List<Order> getOrders() {
		return this.orders;
	}

	/** Assigns a specific List of orders to current car part factory or new car part
	 * factory
	 * 
	 * @param List orders, receives a List that holds Order objects "orders"
	 * @return void
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**Returns the current car part factory's 'defectives' map parameter
	 * 
	 * @param empty
	 * @return Map defectives, returns the List of partMachines of the current car
	 * part factory
	 **/
	public Map<Integer, Integer> getDefectives() {
		return this.defective;
	}

	/** Assigns a specific Map 'defectives' holding integer 'id' as keys and integer
	 * 'count' as values of machines to current car part factory or new car part
	 * factory
	 *
	 * 
	 * @param Map<Integer,Integer> defectives, receives a Map that holds integer
	 * keys and values 'defectives'
	 * @return void
	 **/
	public void setDefectives(Map<Integer, Integer> defectives) {
		this.defective = defectives;
	}

	/**Initializes the Orders list
	 * 
	 * The method reads from the file path received as parameter using a
	 * bufferedReader object. It starts from the second line of the file to avoid
	 * parsing errors for the header row. It creates an array 'param' and adds
	 * values to it every time it splits the file when it finds a ','. The first
	 * value it parses is the id of the order, followed by the name of the customer.
	 * It then creates a Map specific for each order and checks if the 2nd indexes'
	 * length is greater than 0. If it is it creates another array called 'parts'
	 * and splits the line again every time it finds a '-'. It iterates over the
	 * parts array and creates another array which splits everytime it finds a white
	 * space. Finally, it parses an integer value 'partID' from the 0 index of the
	 * last array created and another integer value 'partCountNeeded' from the index
	 * 1 of the last array created. It adds these values to the map and creates the
	 * order, setting its 'fulfilled' variable to false.
	 * 
	 * @param String path, receives a path to a CSV file 
	 * @return void
	 **/
	public void setupOrders(String path) throws IOException {
		String line;
		try (BufferedReader buffReader = new BufferedReader(new FileReader(path))) {
			buffReader.readLine();
			while ((line = buffReader.readLine()) != null) {
				String[] param = line.split(",");
				int id = Integer.parseInt(param[0].trim());
				String customerName = param[1].trim();
				Map<Integer, Integer> requestedParts = new HashTableSC<Integer, Integer>(11, basicHashFunction);
				if (param.length > 2 && param[2].length() != 0) {
					String partParams = param[2].replace("(", "").replace(")", "");
					String[] parts = partParams.split("-");
					for (int i = 0; i < parts.length; i++) {
						String partInfo = parts[i];
						String[] finalPartInfo = partInfo.trim().split(" ");
						int partID = Integer.parseInt(finalPartInfo[0]);
						int partCountNeeded = Integer.parseInt(finalPartInfo[1]);
						requestedParts.put(partID, partCountNeeded);
					}
				}
				Order order = new Order(id, customerName, requestedParts, false);
				this.orders.add(order);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**Initializes the Machines list
	 * 
	 * The method reads from the file path received as parameter using a
	 * bufferedReader object. It starts from the second line of the file to avoid
	 * parsing errors for the header row. Initializes an int variable 'machineID' to
	 * 0.It creates an array 'param' and adds values to it every time it splits the
	 * file when it finds a ','. It increments the machineId every time it reads a
	 * new line. The first value it parses is the integer 'id' of the Car Part
	 * produced by the machine, the String holding the name of the part, the double
	 * value of the weight of the part, the double value of the weight error
	 * produced by the machine, the integer value period of the machine, the integer
	 * value of the chance of producing a defective part of the machine. It creates
	 * a CarPart object and sets its defective status to false for the time being.
	 * It then creates the part machine using the values parsed, and calls the
	 * defectiveOrNot method to determine the defective status of the part, sets its
	 * defective status to the value returned by the call to the defectiveOrNot
	 * method, and finally it sets the part in the machine and adds the machine to
	 * the list. Additionally, it also sets up the Map 'partCatalog'.It iterates
	 * over the final list of machine, putting as key the id of the machine, and the
	 * part it produces as value.
	 * 
	 * @param String path, receives the file path to a CSV file
	 * @return void
	 **/
	public void setupMachines(String path) throws IOException {
		String line;
		int machineID = 0;
		try (BufferedReader buffReader = new BufferedReader(new FileReader(path))) {
			buffReader.readLine();
			while ((line = buffReader.readLine()) != null) {
				machineID++;
				String[] carPartParam = line.split(",");
				int id = Integer.parseInt(carPartParam[0].trim());
				String partName = carPartParam[1].trim();
				double weight = Double.parseDouble(carPartParam[2].trim());
				CarPart p1 = new CarPart(id, partName, weight, false);
				double weightError = Double.parseDouble(carPartParam[3].trim());
				int period = Integer.parseInt(carPartParam[4].trim());
				int chanceOfDefective = Integer.parseInt(carPartParam[5].trim());
				PartMachine machine = new PartMachine(machineID, p1, period, weightError, chanceOfDefective);
				p1.setDefective(machine.defectiveOrNot());
				machine.setPart(p1);
				this.machines.add(machine);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		for (int i = 0; i < this.machines.size(); i++) {
			this.partCatalog.put(this.machines.get(i).getPart().getId(), this.machines.get(i).getPart());
		}
	}

	/**Initializes the production bin of the current Car Part Factory
	 * 
	 * The method creates a Stack that holds CarPart objects and iterates over the list of the machines of the current 
	 * CarPartFactory object. It gets each machine's conveyor belt's front element and checks if it is null or a CarPart,
	 * if it is a CarPart it pushes it to the productionBin.
	 * 
	 * @param empty
	 * @return void
	**/
	public void setupProductionBin() {
		Stack<CarPart> productionBin = this.getProductionBin();
		for (PartMachine machine : this.getMachines()) {
			if (machine.getConveyorBelt().front() != null) {
				productionBin.push(machine.getConveyorBelt().front());
			}
		}
	}
	
	/**Initializes the defectives map of the current Car Part Factory
	 * 
	 * The method creates a Map that holds integer keys and values, representing the ID of the car part and the amount of defective
	 * car parts with this ID, respectively. It iterates over the list of machines and get the ID of the part that the machine
	 * produces, initializes a defectiveCount variable to 0 and adds the ID and the count variable to the map.
	 * 
	 * @param empty
	 * @return void
	**/
	public void setupDefectives() {
		Map<Integer,Integer> defective = new HashTableSC<Integer, Integer>(11, new BasicHashFunction());
		for(PartMachine machine : this.getMachines()) {
			int defectiveCount = 0;
			int partID = machine.getPart().getId();
			defective.put(partID, defectiveCount);
			
		}
	}
	
	/**Initializes the Inventory map of the current Car Part Factory
	 * 
	 * This method iterates over the list of machines of the current Car Part Factory, getting the ID of the part produced
	 * by the machine and creating an empty list of CarPart objects and add the ID as key and the list as value to the Inventory map.
	 * 
	 * @param empty
	 * @return void
	**/
	public void setupInventory() {
		this.getInventory().clear();
		List<PartMachine> machines = this.getMachines();
		for (int i = 0; i < machines.size(); i++) {
			int id = machines.get(i).getPart().getId();
			List<CarPart> numberOfParts = new ArrayList<CarPart>();
			this.getInventory().put(id, numberOfParts);
		}

	}
	
	/**Stores Car Parts in the Inventory map of the current Car Part Factory
	 * 
	 * This method empties the Car Part Factory's Production Bin into a temporary stack, gets the ID and the defective status of the 
	 * popped element. Gets the list of Car Parts with the current part's ID from the Inventory, if the current Car Part is not
	 * defective then it is added to this list. If the part is defective, then it checks if the defectives map already has a key with that
	 * ID, if it does not, then it creates the entry with the ID of the part as the key, and 1 as the count of defectives. If the key already
	 * exists, then it only adds 1 to the amount of total defective parts with that ID. 
	 * 
	 * @param empty
	 * @return void
	**/
	public void storeInInventory() {
		Stack<CarPart> tempStack = new LinkedStack<CarPart>();
		while (!this.getProductionBin().isEmpty()) {
			CarPart currPart = this.getProductionBin().pop();
			tempStack.push(currPart);
			int currPartID = currPart.getId();			
			List<CarPart> tempList = this.getInventory().get(currPartID);
			boolean defective = currPart.isDefective();
			if (!defective) {
				tempList.add(currPart);
			} else {
				if(!this.getDefectives().containsKey(currPartID)) {
					this.getDefectives().put(currPartID, 1);
				}
				else {
					this.getDefectives().put(currPartID, this.getDefectives().get(currPartID)+1);
				}
			}
		}

	}

	/**Runs the factory for the given amount of minutes each day.
	 * 
	 * The method will call on the produceCarPart method for each machine in the Car Part Factory's machine list,
	 * checking if the item produced by the machine is null or not. If the call to the produceCarPart method produces
	 * an actual CarPart, it will get added to the production bin. When all the minutes have passed, the method resets every
	 * machine's conveyor belt, and adds the items to the Inventory. Lastly, when all the days have passed, the method
	 * calls on the processOrders method to start fulfilling orders with the current Inventory.
	 * 
	 * @param int days, int minute. The method receives the integer amount of minutes that each day the Car Part Factory 
	 * will run for and the amount of days it will run.
	 * @return void
	**/
	public void runFactory(int days, int minutes) {
		List<PartMachine> machines = this.getMachines();
		for (int i = 1; i <=days; i++) {
			for (int j = 1; j <= minutes; j++) {
				for (PartMachine machine : machines) {
					CarPart partProduced = machine.produceCarPart();
					if (partProduced != null) {
						this.getProductionBin().push(partProduced);
					}
				}
			}
			for (PartMachine machine : machines) {
				while(! machine.getConveyorBelt().isEmpty()) {
					CarPart frontElem = machine.getConveyorBelt().front();
					if(machine.getConveyorBelt().front()!=null) {
						machine.getConveyorBelt().dequeue();
						this.getProductionBin().push(frontElem);
					}
					else {
						machine.getConveyorBelt().dequeue();
					}
				}
				machine.resetConveyorBelt();
			}
			this.storeInInventory();
		}
		this.processOrders();
	}

	/**Processes orders based on part availability
	 * 
	 * This method iterates over the list of orders, sets the order's fulfilled status to true, and then iterates over the 
	 * keys of the Order's requestedParts map. It checks if the current Inventory contains the ID of the requested parts. If
	 * it does not, then it sets the order's fulfilled status to false because the order will not be able to be completed.
	 * If the Inventory does contain the ID of the requested part, it checks if the size of the list of those CarParts is
	 * less than the number of those parts requested. If it is lesser, then the fulfillment status of the order will be set
	 * to false. If the size of the list is greater, then it creates a counter for keeping track of how many parts have been 
	 * removed from the Inventory. Once it removes the necessary amount of parts to fulfill the order, it repeats the process 
	 * for the next machine. It does not need to set the order's fulfilled status to true since it is set at the beginning. 
	 * @param empty
	 * @return void
	**/
	public void processOrders() {
		for (Order order : this.getOrders()) {
			order.setFulfilled(true);
			for(int orderPartID : order.getRequestedParts().getKeys()) {
				if(!this.getInventory().containsKey(orderPartID)) {
					order.setFulfilled(false);
					break;
				}else if(this.getInventory().get(orderPartID).size()< order.getRequestedParts().get(orderPartID)){
					order.setFulfilled(false);
					break;
				}
			}
			if(order.isFulfilled()) {
				for(int orderPartID : order.getRequestedParts().getKeys()) {
					int counter = 0;
					while(counter != order.getRequestedParts().get(orderPartID)) {
						this.getInventory().get(orderPartID).remove(0);
						counter++;
					}
				}
			}	
		}
	}

	/**
	 * Generates a report indicating how many parts were produced per machine, how
	 * many of those were defective and are still in inventory. Additionally, it
	 * also shows how many orders were successfully fulfilled.
	 */
	public void generateReport() {
		String report = "\t\t\tREPORT\n\n";
		report += "Parts Produced per Machine\n";
		for (PartMachine machine : this.getMachines()) {
			report += machine + "\t(" + this.getDefectives().get(machine.getPart().getId()) + " defective)\t("
					+ this.getInventory().get(machine.getPart().getId()).size() + " in inventory)\n";
		}

		report += "\nORDERS\n\n";
		for (Order transaction : this.getOrders()) {
			report += transaction + "\n";
		}
		System.out.println(report);
	}

}
