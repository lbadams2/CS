import java.util.LinkedList;
import java.util.Scanner;


public class CarWashUser {

	public static void main(String[] args){
		new CarWashUser().run();
	}
	
	public void run() {
		final String ARRIVAL_TIME = "\nPlease enter the mean arrival time: ";
		final String SERVICE_TIME = "\nPlease enter the mean service time: ";
		final String MAX_ARRIVAL = "\nPlease enter the maximum arrival time: ";
		final String OUT_OF_RANGE = "The input must consist of a non-negative integer less than the sentinal.";
		CarWash carWash;
		Scanner sc = new Scanner(System.in);
		int meanArrivalTime,
			meanServiceTime,
			maxArrivalTime;
		
		while(true) {
			try {
				System.out.println(ARRIVAL_TIME);
				meanArrivalTime = sc.nextInt();
				System.out.println(SERVICE_TIME);
				meanServiceTime = sc.nextInt();
				System.out.println(MAX_ARRIVAL);
				maxArrivalTime = sc.nextInt();
			
				if(meanArrivalTime < 0 || meanServiceTime < 0 || maxArrivalTime < 0)
					throw new NumberFormatException(OUT_OF_RANGE);
				carWash = new CarWash(meanArrivalTime, meanServiceTime, maxArrivalTime);
				
				int arrivalTime = carWash.generateArrivalTime();
				while(arrivalTime != -1) {
					carWash.process(arrivalTime);
					arrivalTime = carWash.generateArrivalTime();
				}
				break;
			} catch(Exception e) {
				System.out.println(e);
				sc.nextLine();
			}
		}
		carWash.finishUp();
		printResults(carWash);
		sc.close();
	}
	
	public void printResults(CarWash carWash){
		final String RESULTS_HEADING = "\nHere are the results of the simulation\n";
		
		LinkedList<String> results = carWash.getResults();
		System.out.println(RESULTS_HEADING);
		for(String s : results)
			System.out.print(s);
	}	
}