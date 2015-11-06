
all:
	javac *.java

clean:
	rm *.class
	
test:
	@echo "Compiling files"
	javac *.java
	@echo "Running unit tests"
	java TestRunner UnitTests
	@echo "Removing class-files"
	rm *.class
