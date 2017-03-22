Programming Exercise 2.12:
windows command line - source files(Employee.java, FullTimeEmployee.java, FullTimeEmployeeTest.java) and jars (junit, hamcrest) in same directory
javac -cp * Employee.java FullTimeEmployee.java FullTimeEmployeeTest.java
java -cp .;* org.junit.runner.JUnitCore FullTimeEmployeeTest

or put the three source files in a project in eclipse and add the jars to the project build path

Lab 3 jars in different directory
javac -cp ..\Lab2\* Fibonacci.java FibonacciTest.java
java -cp .;..\Lab2\* org.junit.runner.JUnitCore FibonacciTest