Problem 1
QueueArrayTest is for both QueueArrayFixedImpl and QueueArrayFloatingImpl, just change the object created in the before() method
QueueLinkedListTest is for QueueLinkedListImpl because the is_full() method doesn't apply 
javac -cp * *.java
java -cp .;* org.junit.runner.JUnitCore QueueArrayTest
java -cp .;* org.junit.runner.JUnitCore QueueLinkedListTest

Problem 2
javac *.java
java CarWashUser

Problem 3
javac *.java
java InfixToPostfix