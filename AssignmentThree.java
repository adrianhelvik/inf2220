import java.util.*;

public class AssignmentThree {
    public static void main( String[] args ) {
        
        if ( args.length < 2 ) {
            System.out.println( "Not enough arguments. Requires args: <needle> <haystack>" );
            System.exit(0);
        }
        
        String needle = args[0];
        String haystack = args[1];

        StringSearch search = new StringSearch( needle, haystack );

        System.out.println( "Length of haystack:                  " + haystack.length() );
        System.out.println( "Length of needle:                    " + needle.length() );
        System.out.println( "Number of iterations:                " + search._iterations );
        System.out.println( "Where # to assign badCharacterShift: " + search._bcsIterations );
        System.out.println( "# of iterations - bcs array init:    " + ( search._iterations - search._bcsIterations ) );

        System.out.println( "Result: " + search );
    }
}
