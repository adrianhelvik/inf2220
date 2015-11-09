
all:
	javac *.java

clean:
	rm *.class

c:
	rm *.class
	
test:
	@clear
	@echo "Compiling files"
	javac *.java
	@echo "Running unit tests"
	java TestRunner TestsForStringSearch
	@echo "Removing class-files"
	rm *.class
