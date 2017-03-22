
public class Student implements Comparable{

	String lastName,
			firstName,
			middleName;
	long id;
	
	public Student(String firstName, String lastName, String middleName, long id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.id = id;
	}

	public int compareTo(Object arg0) {
		Student other = (Student)arg0;
		Long thisID = this.id;
		Long otherID = other.id;
		int lastComp = this.lastName.compareTo(other.lastName);
		int firstComp = this.firstName.compareTo(other.firstName);
		int middleComp = this.middleName.compareTo(other.middleName);
		
		if( lastComp != 0)
			return lastComp;
		else if(firstComp != 0)
			return firstComp;
		else if(middleComp != 0)
			return middleComp;
		else
			return thisID.compareTo(otherID);
	
	}
	
	public String toString() {
		String idString = new Long(id).toString();
		return lastName + ", " + firstName + " " + middleName + " " + idString; 
	}
}
