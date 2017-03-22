
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
		return thisID.compareTo(otherID);
	}
	
	public String toString() {
		String idString = new Long(id).toString();
		return lastName + ", " + firstName + " " + middleName + " " + idString; 
	}
}
