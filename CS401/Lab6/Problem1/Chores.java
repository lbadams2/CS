public class Chores  {
      String chore_name;
      int    chore_time;

      public Chores(String c, int t)  { chore_name = c; chore_time = t; }
      public String toString()  {
         return chore_name + ":" + chore_time;
      }
      
      public boolean equals(Object o) {
    	Chores c;
    	if(o instanceof Chores)
    		c = (Chores)o;
    	else
    		return false;
    	
    	if(this.chore_name == c.chore_name && this.chore_time == c.chore_time)
    		return true;
    	
    	return false;
      }
}