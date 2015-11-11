Readme for assignment 3 in INF2220 for Adrian Ødeby Helvik
==========================================================

How to compile and run the program
----------------------------------
make && java AssignmentThree &lsaquo;needle&rsaquo; &lsaquo;haystack&rsaquo;

Note: Add "test" as argument 3 to view test printing
that visually shows what is happening.

How to test
-----------

### About TestRunner
TestRunner accepts a class-name as an argument, and ignores
any methods in the given class that A) don't return boolean
or Boolean or B) has any arguments. The name of the method
is converted from camelCase or snake\_case to space separated
sentences (and numbers are prepended with a space). The tests 
then start and run whatever code is in the test. If the return
value is true, the test has passed and the next test will start,
otherwise a log of the completed tests and their statuses will
be printed. Feel free to use it for any purpose. Could be
useful for assignments.

### Run
Run the command 'make test'.

### Disable single text
If you want to disable a test simply prefix its name with
IGNORE (case insensitive). I prefer IGNORE\_

### What the output should be
The tests make use of the is-functions, which tests whether
the first and second arguments are equal and logs it to the
console. The third argument is a string specifying a reason
for why the test should return a given value. Needs to be
implemented into PerformTests though.

Explanation of algorithm
------------------------
The search algorithm is 'bad character shift', and its primary
part is located under StringSearch.search().

I start by setting the offset variable to be the index of the
last character of the needle and search backwards through the
needle and haystack. If I a non-matching letter pair is found
I increment base with its bad char shift number and reset the
offset (too the length of needle).

In createBcs() I first determine the char shift for the letters
in the needle, and then determine it for all letters whose value
is 0 &ndash; which is the default value in an int-array and is the
value of the last letter in the needle.

Notes on variable names and uses:
- base + offset: index in haystack at any point in the search
- offset: index in needle at any point in the search
- bsc, createBsc: I use bsc as an abbreviation for bad character shift

Which file includes the main-method
-----------------------------------
AssignmentThree.java

Status of delivery
------------------
I fail to find any errors :)
