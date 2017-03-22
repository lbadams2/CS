import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class CarWash {

	public final String OVERFLOW = " (Overflow)\n";
	protected final String HEADING = "\n\nTime\tEvent\t\tWaiting Time\n";
	protected static final int INFINITY = 10000;
	protected static final int MAX_SIZE = 5;
	
	protected int waitingTime,
				  currentTime,
				  nextDepartureTime,
				  numberOfCars,
				  sumOfWaitingTimes,
				  meanArrivalTime,
				  meanServiceTime,
				  overflows,
				  lastDepartureTime,
				  lastArrivalTime,
				  maxArrivalTime;
	
	protected LinkedList<String> results;
	protected Queue<Car> carQueue;
	protected Random random;
	
	public CarWash(int meanArrivalTime, int meanServiceTime, int maxArrivalTime) {
		carQueue = new LinkedList<Car>();
		results = new LinkedList<String>();
		results.add(HEADING);
		currentTime = 0;
		numberOfCars = 0;
		waitingTime = 0;
		sumOfWaitingTimes = 0;
		nextDepartureTime = INFINITY;
		this.maxArrivalTime = maxArrivalTime;
		this.meanArrivalTime = meanArrivalTime;
		this.meanServiceTime = meanServiceTime;
		random = new Random(100);
	}
	
	public LinkedList<String> process (int nextArrivalTime) {
		final String BAD_TIME = "The time of the next arrival cannot be less than the current time";
		
		if(nextArrivalTime < currentTime)
			throw new IllegalArgumentException("BAD_TIME");
		while(nextArrivalTime >= nextDepartureTime) // call processDeparture 
			processDeparture(); // processDeparture increments nextDepartureTime
		return processArrival(nextArrivalTime);
	}
	
	protected LinkedList<String> processArrival(int nextArrivalTime) {
		final String ARRIVAL = "\tArrival";
		currentTime = nextArrivalTime;
		results.add(Integer.toString(currentTime) + ARRIVAL);
		
		if(carQueue.size() == MAX_SIZE){
			results.add(OVERFLOW);
			overflows++;
		}
		else {
			numberOfCars++;
			if(nextDepartureTime == INFINITY) // no car being currently washed
				nextDepartureTime = currentTime + generateServiceTime(); // put car in wash, which is simulated by incrementing nextDepartureTime
			else 
				carQueue.add(new Car(nextArrivalTime)); // there is a car currently being washed, add car to the queue
			results.add("\n");
		}
		return results;
	}
	
	protected LinkedList<String> processDeparture() {
		final String DEPARTURE = "\tDeparture\t\t";
		
		int arrivalTime;
		
		currentTime = nextDepartureTime;
		results.add(Integer.toString(currentTime) + DEPARTURE + Integer.toString(waitingTime) + "\n");
		
		// simulate washing a car by removing a car from the queue and adding to the nextDepartureTime
		if(!carQueue.isEmpty()) {
			Car car = carQueue.remove(); 
			arrivalTime = car.getArrivalTime();
			waitingTime = currentTime - arrivalTime;
			sumOfWaitingTimes += waitingTime;
			nextDepartureTime = currentTime + generateServiceTime();
		}
		else {
			waitingTime = 0;
			lastDepartureTime = nextDepartureTime;
			nextDepartureTime = INFINITY;
		}
		return results;
	}
	
	public LinkedList<String> finishUp() {
		while(nextDepartureTime < INFINITY)
			processDeparture();
		return results;
	}
	
	public LinkedList<String> getResults() {
		final String NO_CARS_MESSAGE = "There were no cars in the wash\n";
		final String AVERAGE_WAITING_TIME_MESSAGE = "\n\nThe average waiting time was ";
		final String AVERAGE_QUEUE_LENGTH_MESSAGE = "\n\nThe average queue length, in minutes, was ";
		
		if(numberOfCars == 0)
			results.add(NO_CARS_MESSAGE);
		else {
			double avgWaitTime = (double)sumOfWaitingTimes/numberOfCars;
			double avgQueueLength = (double)sumOfWaitingTimes/lastDepartureTime;
			results.add(AVERAGE_WAITING_TIME_MESSAGE + Double.toString(Math.round(avgWaitTime * 10)/10.0) + " minutes per car.");
			results.add(AVERAGE_QUEUE_LENGTH_MESSAGE + Double.toString(Math.round(avgQueueLength * 10)/10.0) + " cars per minute.");
			results.add("\n\nThe number of overflows was " + overflows + ".");
		}
		return results;
	}
	
	// Poisson distribution: f(x) = exp(-timeTilNext / meanArrivalTime)
	// returns the probability that the next time will be at least timeTilNext minutes from now
	protected int generateArrivalTime() {
		double randomDouble = random.nextDouble(); // returns a double between 0 inclusive to 1 exclusive
		int timeTilNext, arrivalTime = 0;
		
		timeTilNext = (int)Math.round(-meanArrivalTime * Math.log(1 - randomDouble));
		arrivalTime = currentTime + timeTilNext;
		if(arrivalTime > maxArrivalTime)
			return -1;
		else
			return arrivalTime;
	}
	
	protected int generateServiceTime(){
		int nextServiceTime = 0;
		double randomDouble = random.nextDouble();
		nextServiceTime = (int)Math.round(-meanServiceTime * Math.log(1 - randomDouble));
		return nextServiceTime;
	}
}