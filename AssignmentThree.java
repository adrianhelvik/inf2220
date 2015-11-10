import java.util.*;

public class AssignmentThree {

    public static final char WILDCARD = '_';

    public static void main( String[] args )
    {
        if ( args.length < 2 ) {
            System.out.println("Not enough arguments. Requires needle and haystack");
            System.exit(0);
        }

        String haystack = "";
        
        for ( int i = 1; i < args.length; i++ ) {
            haystack += args[i] + " ";
        }

        haystack = haystack.substring( 0, haystack.length() - 1 );
        
        p( search( args[ 0 ], haystack ) );
    }

    static Map<Integer, String> search( String needle, String haystack )
    {
        System.out.println("Searching for '" + needle + "' in '" + haystack + "'");

        Map<Integer, String> res = new HashMap<>();
        ArrayList<Integer> bcs = createBcs( needle );

        int base = 0;
        int last = needle.length() - 1;
        int offset = last;
        
        while ( true ) {

            if ( base + last >= haystack.length() ) {
                break;
            }
            
            char n = needle.charAt( offset );
            char h = haystack.charAt( base + offset );

            if ( n != h && n != '_' ) {
                base += bcs.get( n );
                offset = last;
            }

            while ( n == h || n == '_' ) {

                if ( offset == 0 ) {
                    res.put( base, haystack.substring( base, base + needle.length() ) );
                    
                    offset = last;
                    n = needle.charAt( offset );
                    base += bcs.get( n );

                    break;
                } else {
                    offset--;
                }

                n = needle.charAt( offset );
                h = haystack.charAt( base + offset );
            }
        }

        return res;
    }

    static void p( Object... args )
    {
        for ( Object o : args ) {
            System.out.print( o );
        }
        System.out.println();
    }

    static void dly()
    {
        new Scanner(System.in).nextLine();
    }

    static ArrayList<Integer> createBcs( String needle )
    {
        ArrayList<Integer> bcs = new ArrayList<>( 256 );

        int maxJump = needle.length();

        for ( int i = 0; i < needle.length(); i++ ) {
            if ( needle.charAt( i ) == WILDCARD ) {
                maxJump = i+1;
            }
        }

        for ( int i = 0; i < 256; i++ ) {
            bcs.add( maxJump );
        }

        for ( int i = 0; i < needle.length(); i++ ) {
            int a = needle.length() - i;
            bcs.set( (int) needle.charAt( i ), a < maxJump ? a : maxJump );
        }

        return bcs;
    }
}

