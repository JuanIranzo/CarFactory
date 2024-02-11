package main;

import data_structures.ListQueue;
import interfaces.List;
import interfaces.Queue;
import java.util.Random;

/**@author Juan Iranzo <juan.iranzo@upr.edu>
 * This class represents an object called 'PartMachine', and defines its parameters and behaviors
 * 
**/
public class PartMachine {
    private int id;
    private CarPart p1;
    private int period;
    private double weightError;
    private int chanceOfDefective;
    private Queue<Integer> timer = new ListQueue<Integer>();
    private Queue<CarPart> conveyorBelt = new ListQueue<CarPart>();
    private int totalPartsProduced;
    
    /**This is the constructor for the PartMachine object
	 * 
	 * @param 'id, 'p1', 'period', 'weightError', 'chanceOfDefective'. This constructor receives an integer value
	 * called 'id' which represents the identifier for the partMachine, a carPart object 'p1' which represents the part produced by the machine.
	 * The constructor also receives a integer value called 'period' which represents the period the machine takes to produce a part. 
	 * Additionally, using the period received, it constructs a Queue 'timer' that goes from period-1 to 0. It also constructs a Queue 
	 * 'conveyorBelt' that holds CarPart objects and null elements. It initializes the conveyor belt with 10 spaces, all set to null. 
	 * Lastly, it receives a double value 'weightError', which sets the machine's weight error. Lastly, it receives an integer value 'chanceOfDefective'
	 * representing the chance of the machine producing a defective part 
	**/
    public PartMachine(int id, CarPart p1, int period, double weightError, int chanceOfDefective) {
    	Queue<Integer> machineTimer = new ListQueue<Integer>();
    	Queue<CarPart> conveyorBelt = new ListQueue<CarPart>();	
        this.id=id;
        this.p1=p1;
        this.period=period;
        this.weightError=weightError;
        this.chanceOfDefective=chanceOfDefective;
        int newPeriod = this.period-1;
		while(newPeriod>=0) {
			   machineTimer.enqueue(newPeriod);
			   newPeriod--;
		   }
		while(conveyorBelt.size()<10) {
			conveyorBelt.enqueue(null);
		}
        this.timer= machineTimer;
        this.conveyorBelt=conveyorBelt;
    }
    
    /**Returns the current partMachine's ID parameter
	 * 
	 * @param empty
	 * @return int ID, returns the ID parameter of the part machine as an int
	**/
    
    public int getId() {
       return this.id;
    }
     
    /**Assigns a specific id to current partMachine or new partMachine 
     * 
   	 * @param id, receives a integer value "id"
   	 * @return void
   	**/
    
    public void setId(int id) {
        this.id=id;
    }
    
    /**Returns the current partMachine's period parameter
   	 * 
   	 * @param empty
   	 * @return int period, returns the period parameter of the part machine as an int
   	**/
    
    public int getPeriod() {
    		return this.period;
    }
    
    
    /**Assigns a specific period to current partMachine or new partMachine 
     * 
   	 * @param period, receives a integer value "period"
   	 * @return void
   	**/
    
    public void setPeriod(int period) {
    		this.period=period;
    }
    
    /**Returns the current partMachine's ID parameter
   	 * 
   	 * @param empty
   	 * @return Queue<Integer> timer, returns the timer parameter of the part machine as a Queue that holds integer values
   	**/
    
    public Queue<Integer> getTimer() {
       return this.timer;
    }
    
    /**Assigns a specific timer Queue to current partMachine or new partMachine 
     * 
   	 * @param timer, receives a Queue holding integer values "timer"
   	 * @return void
   	**/
    
    public void setTimer(Queue<Integer> timer) {
        this.timer = timer;
    }
    
    /**Returns the current partMachine's part parameter
   	 * 
   	 * @param empty
   	 * @return CarPart p1, returns the part produced by the part machine as a CarPart object
   	**/
    
    public CarPart getPart() {
    		return this.p1;
    }
    
    /**Assigns a specific car part to current partMachine or new partMachine 
     * 
   	 * @param part1, receives a car part object "part1"
   	 * @return void
   	**/
    
    public void setPart(CarPart part1) {
        this.p1=part1;
    }
    
    /**Returns the current partMachine's ConveyorBelt parameter
   	 * 
   	 * @param empty
   	 * @return Queue<CarPart> ConveyorBelt, returns the conveyorBelt parameter of the part machine as a Queue holding Car part objects, it initializes all of its
   	 * spaces to null
   	**/
    
    public Queue<CarPart> getConveyorBelt() {
    		
        return this.conveyorBelt;
    }
    
    /**Assigns a specific conveyor belt to current partMachine or new partMachine 
     * 
   	 * @param conveyorBelt, receives a Queue holding car part objects "conveyorBelt"
   	 * @return void
   	**/
    public void setConveyorBelt(Queue<CarPart> conveyorBelt) {
    		this.conveyorBelt=conveyorBelt;
    }
    /**Returns the current partMachine's totalPartsProduced parameter
   	 * 
   	 * @param empty
   	 * @return int totalPartsProduced, returns the totalPartsProduced parameter of the part machine as an int
   	**/
    
    public int getTotalPartsProduced() {
         return this.totalPartsProduced;
    }
    
    /**Assigns a specific number of parts produced to current partMachine or new partMachine 
     * 
   	 * @param count, receives a integer value "count"
   	 * @return void
   	**/
    
    public void setTotalPartsProduced(int count) {
    		this.totalPartsProduced=count;
    }
    
    /**Returns the current partMachine's partWeightError parameter
   	 * 
   	 * @param empty
   	 * @return double partWeightError, returns the partWeightError parameter of the part machine as a double
   	**/
    
    public double getPartWeightError() {
        return this.weightError;
    }
    
    /**Assigns a specific partWeightError to current partMachine or new partMachine 
     * 
   	 * @param partWeightError, receives a integer value "partWeightError"
   	 * @return void
   	**/
    
    public void setPartWeightError(double partWeightError) {
        this.weightError=partWeightError;
    }
    
    /**Returns the current partMachine's chanceOfDefective parameter
   	 * 
   	 * @param empty
   	 * @return int chanceOfDefective, returns the chanceOfDefective parameter of the part machine as an int
   	**/
    
    public int getChanceOfDefective() {
        return this.chanceOfDefective;
    }
    
    /**Assigns a specific chance of producing a defective part to current partMachine or new partMachine 
     * 
   	 * @param chanceOfDefective, receives a integer value "chanceOfDefective"
   	 * @return void
   	**/
    
    public void setChanceOfDefective(int chanceOfDefective) {
        this.chanceOfDefective=chanceOfDefective;
    }
    
	/**Resets the elements placed on the conveyor belts
	 * 
	 * Dequeues all the elements of the conveyor belt and enqueues null 10 times
	 * @param empty
	 * @return void
	**/
    public void resetConveyorBelt() {
        while(!this.conveyorBelt.isEmpty()) {
        		this.conveyorBelt.dequeue();
        }
        while(this.conveyorBelt.size()!=10) {
        		this.conveyorBelt.enqueue(null);
        }
    }
    
	/**Simulates the timer ticking
	 * 
	 * The method dequeues the element at the front of the conveyor belt and enqueues it, 
	 * returning the element previously at the front
	 * @param empty
	 * @return front, returns the element at the front of the timer Queue, regardless if it is
	 * a carPart or null
	 * 
	**/
    public int tickTimer() {
       int front = this.timer.dequeue();
       this.timer.enqueue(front);
       return front;
    }

	/**Ticks the timer, checks if it reached 0 and places a part in the conveyor belt
	 * 
	 * It gets all of the parameters for the carPart that this machine produces, assigns a random
	 * weight to the part based on the weight error parameter of the machine. It generates a random number, calculates
	 * the minimum and maximum weight the part could have based on the actual weight of the part and the weight error
	 * margin of the machine, scales the weight using the product of the difference between the maximum and minimum weight
	 * and the random double number created, and adds this to the minimum weight. It then calls the defectiveOrNot
	 * method to check if the part is defective or not, set its defective status to what the method returned
	 * checks if the front of the timer i 0, if it is, it creates the part using the obtained parameters
	 * and places it at the end of the conveyor belt. If the front of the timer is not 0 it enqueues null
	 * to the conveyor belt. Finally it ticks the timer using the tickTimer method and returns the front of the conveyorBelt.
	 * @param empty
	 * @return CarPart, returns the front of the conveyor belt
	**/
    public CarPart produceCarPart() {

    		if(this.getTimer().front()==0){
        		int id = this.getPart().getId();
        		String name = this.getPart().getName();
        		double actualWeight = this.getPart().getWeight();
        		double weightError = this.getPartWeightError();
        		double maxWeight = actualWeight+weightError;
        		double minWeight = actualWeight-weightError;
        		double range = maxWeight-minWeight;
        		Random randomWeight = new Random();
        		double scaledWeight = randomWeight.nextDouble()*range;
        		double finalRandomWeight = scaledWeight + minWeight;
        		boolean isDefective = this.defectiveOrNot();
    			CarPart newCarPart = new CarPart(id, name, finalRandomWeight, isDefective);
    			this.getConveyorBelt().enqueue(newCarPart);
    			this.setTotalPartsProduced(this.getTotalPartsProduced() + 1);
       }
		 else { 
			this.getConveyorBelt().enqueue(null);
			
			 }
    		
    		this.tickTimer();
    		return this.getConveyorBelt().dequeue();
    }
    
	/**Determines whether the car part being produced is defective or not
	 * 
	 * The method gets the total parts produced of the part machine and the defective chance of the machine
	 * and calculates the remainder of the division. If the remainder is equal to 0, the part will be defective,
	 * otherwise not.
	 * @param empty
	 * @return boolean isDefective, returns the defective value of the carPart being produced
	**/
    	public boolean defectiveOrNot() {
    		int parts = this.getTotalPartsProduced();
    		int defectiveChance = this.getChanceOfDefective();
    		return parts%defectiveChance == 0;
    		
    	}

    /**
     * Returns string representation of a Part Machine in the following format:
     * Machine {id} Produced: {part name} {total parts produced}
     */
    @Override
    public String toString() {
        return "Machine " + this.getId() + " Produced: " + this.getPart().getName() + " " + this.getTotalPartsProduced();
    }
    /**
     * Prints the content of the conveyor belt. 
     * The machine is shown as |Machine {id}|.
     * If the is a part it is presented as |P| and an empty space as _.
     */
    public void printConveyorBelt() {
        // String we will print
        String str = "";
        // Iterate through the conveyor belt
        for(int i = 0; i < this.getConveyorBelt().size(); i++){
            // When the current position is empty
            if (this.getConveyorBelt().front() == null) {
                str = "_" + str;
            }
            // When there is a CarPart
            else {
                str = "|P|" + str;
            }
            // Rotate the values
            this.getConveyorBelt().enqueue(this.getConveyorBelt().dequeue());
        }
        System.out.println("|Machine " + this.getId() + "|" + str);
    }
}
