import java.util.*;

public class AssignmentThree {

    static Map<Integer, String> _testResults; // used for unit testing

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

        boolean testing = args.length > 2 && args[2].equals("test");

        StringSearch search = new StringSearch(needle, haystack);

        if (testing)
            search.testing = true;

        Map<Integer, String> results = search.searchAndMap();

        if (testing)
            AssignmentThree._testResults = results;

        p( "needle:", needle, "haystack:", haystack );
        
        p( "Results:", results );

    }

    /**
     * Print a variadic number of arguments on same line separated by a space. Print newline after all arguments are printed.
     */
    static void p( Object... args ) {
        for ( Object o : args ) {
            System.out.print( o + " " );
        }
        System.out.println();
    }
}

