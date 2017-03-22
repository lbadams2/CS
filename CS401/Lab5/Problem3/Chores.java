public class Chores  {
      String chore_name;
      int    chore_time;

      public Chores(String c, int t)  { chore_name = c; chore_time = t; }
      public String toString()  {
         return chore_name + ":" + chore_time;
      }
}