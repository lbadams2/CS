
public class Student implements Comparable<Student> 
{
    protected String name;

    protected double gpa;


    /**
     *  Initializes this Student object from a specified name and grade point average.
     *
     *  @param name - the specified name.
     *  @param gpa - the specified grade point average.
     *
     */     
    public Student (String name, double gpa) 
    {        
        this.name = name;
        this.gpa = gpa;
    } // constructor

        
    /**
     *  Compares this Student object to a specified Student object by 
     *  grade point average.
     *
     *  @param otherStudent - the specified Student object.
     *
     *  @return a negative integer, 0, or a positive integer, depending
     *  on whether this Student object’s grade point average is less than,
     *  equal to, or greater than (within DELTA) otherStudent’s grade point average.
     *      
     */                         
    public int compareTo (Student otherStudent) 
    {
        final double DELTA = 0.0000001;
        
        if (gpa < otherStudent.gpa - DELTA)
            return -1;
        if (gpa > otherStudent.gpa + DELTA)
            return 1;
        return 0;
    } // method compareTo

    
    /**
     *  Returns a String representation of this Student object.
     *
     *  @return  a String representation of this Student object: name “ “ gpa
     *
     */
    public String toString() 
    {
        return name + "  " + gpa;
    } // method toString
    
    public boolean equals(Object arg0) 
    {
    	Student other = (Student)arg0;
    	return this.gpa == other.gpa && this.name == other.name;
    }
} // class Student