all:
	javac *.java

test:
	javac *.java
	java TestRunner TestStringSearch
	rm *.class

clean:
	rm *.class
