Readme for assignment 3 in INF2220 for Adrian Ødeby Helvik
==========================================================

How to compile and run the program
----------------------------------
make && java AssignmentThree <needle> <haystack>

Note: Add "test" as argument 3 to view test printing
that visually shows what is happening.

How to test
-----------
Run the command 'make test'. If you want to disable a test simply
prefix its name with IGNORE (case insensitive).

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
is 0 -- which is the default value in an int-array and is the
value of the last letter in the needle.

Notes on variable names and uses:
    * base + offset: index in haystack at any point in the search
    * offset: index in needle at any point in the search
    * bsc, createBsc: I use bsc as an abbreviation for bad character shift

Which file includes the main-method
-----------------------------------
AssignmentThree.java

Status of delivery
------------------
I fail to find any errors :)
