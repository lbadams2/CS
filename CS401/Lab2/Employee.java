import java.text.DecimalFormat;


public interface Employee {
	
	final static DecimalFormat MONEY = new DecimalFormat ("0.00");
	
	String getName();
	double getGrossPay();
	String toString();

}
