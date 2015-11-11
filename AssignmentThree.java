import java.util.*;

public class AssignmentThree {

    //
    // Main
    //

    /**
     * Search with a needle through another string. Required params: needle, haystack, optional params: --test
     * If the parameter --test is passed, test printing will be shown.
     */
    public static void main( String[] args )
    {
        if ( args.length < 2 ) {
            System.out.println("Not enough arguments. Requires needle and haystack");
            System.exit(0);
        }

        String needle = args[ 0 ];
        String haystack = args[ 1 ];

        AssignmentThree assignment = new AssignmentThree();

        if (args.length > 2 && args[2].equals("--test")) {
            assignment.testPrint = true;
        }

        Map<Integer, String> results = assignment.search( needle, haystack );

        p( "needle:", needle, "haystack:", haystack );
        
        p( "Results:", results );
    }

    //
    // Static
    //

    public static final char WILDCARD = '_';

    /**
     * Print a variadic number of arguments separated by a space. Print newline after.
     */
    static void p( Object... args ) {
        for ( Object o : args ) {
            System.out.print( o + " " );
        }
        System.out.println();
    }

    /**
     * Create bad character shift ArrayList.
     * @return ArrayList containing bad character shift jump lengths for the first 256 characters
     */
    static ArrayList<Integer> createBcs( String needle ) {
        ArrayList<Integer> bcs = new ArrayList<>( 256 );

        int maxJump = needle.length();

        // Find maximum jump length depending on position of last wildcard
        for ( int i = 0; i < needle.length(); i++ ) {
            if ( needle.charAt( i ) == WILDCARD ) {
                maxJump = needle.length() - 1 - i;
            }
        }

        if ( maxJump == 0 ) {
            maxJump = 1;
        }

        // Set all characters to max jump length
        for ( int i = 0; i < 256; i++ ) {
            bcs.add( maxJump );
        }

        // Set jump length for characters in needle
        for ( int i = 0; i < needle.length(); i++ ) {

            int jumpLength = needle.length() - i - 1;

            if ( jumpLength != 0 ) {
                bcs.set( needle.charAt(i), jumpLength);
            }
        }

        return bcs;
    }

    /**
     * Print the haystack, the needle and their position in the search.
     */
    static void printIntermediateSearch( String needle, String haystack, int base, int offset ) {

        String res = "";

        // Add haystack on first line
        res += haystack + "\n";

        // Insert spaces before position dot
        for (int i = 0; i < base+offset; i++) {
            res += " ";
        }

        // Insert position dot
        res += ".\n";

        // Insert spaces before needle
        for (int i = 0; i < base; i++) {
            res += " ";
        }

        // insert needle
        res += needle + "\n";

        System.out.println( res );
    }

    //
    // Attributes
    //

    boolean testPrint = false;

    //
    // Methods
    //

    /**
     * Search through string. Wildcards specified by the character '_'
     * @return A Map mapping from the start of the match in the haystack to the resulting string
     */
    Map<Integer, String> search( String needle, String haystack ) {
        Map<Integer, String> res = new HashMap<>();
        final ArrayList<Integer> bcs = createBcs( needle );

        int base = 0;
        int last = needle.length() - 1;
        int offset = last;

        boolean hasWildcard = false;

        for (char ch : needle.toCharArray()) {
            if (ch == '_') {
                hasWildcard = true;
            }
        }

        while ( true ) {

            // Test printing
            if ( testPrint ) {
                printIntermediateSearch( needle, haystack, base, offset );
            }

            if ( base + last >= haystack.length() ) {
                break;
            }

            char n = needle.charAt( offset );
            char h = haystack.charAt( base + offset );

            if ( n != h && n != '_' ) {

                if ( hasWildcard ) {
                    base++;
                } else {
                    base += bcs.get( h );
                }

                offset = last;
            }

            while ( n == h || n == '_' ) {

                if ( testPrint ) {
                    printIntermediateSearch( needle, haystack, base, offset );
                }

                if ( offset == 0 ) {
                    if ( testPrint ) {
                        p("Found a match");
                    }

                    res.put( base, haystack.substring( base, base + needle.length() ) );

                    offset = last;
                    n = needle.charAt( offset );
                    h = haystack.charAt( base + offset );

                    if (hasWildcard) {
                        base++;
                    } else {
                        base += bcs.get( h );
                    }

                    offset = last;

                    break;
                }

                else {
                    offset--;
                }

                n = needle.charAt( offset );
                h = haystack.charAt( base + offset );
            }

        }

        return res;
    }
}

