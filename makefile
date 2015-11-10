all:
	javac *.java

test:
	javac *.java
	java TestRunner AssignmentThreeTests
	rm *.class

clean:
	rm *.class
