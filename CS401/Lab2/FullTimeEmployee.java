
public class FullTimeEmployee implements Employee {
	
	private String name;
	private double grossPay;
	
	public FullTimeEmployee(){
		final String EMPTY_STRING = "";
		
		name = EMPTY_STRING;
		grossPay = 0.00;
	}
	
	public FullTimeEmployee(String name, double grossPay){
		this.name = name;
		this.grossPay = grossPay;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getGrossPay() {
		return grossPay;
	}
	
	public String toString() {
		final String EMPLOYMENT_STATUS = "FULL TIME";
		return name + MONEY.format(grossPay) + EMPLOYMENT_STATUS;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof FullTimeEmployee))
			return false;
		FullTimeEmployee full = (FullTimeEmployee)obj;
		return name.equals(full.name) && MONEY.format(grossPay).equals(MONEY.format(full.grossPay));
	}
}
