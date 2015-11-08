import java.util.*;

public class StringSearch {

    int _iterations = 0;

    char[] badCharShift;
    String needle;
    String haystack;
    String[] result;

    public StringSearch( String needle, String haystack ) {
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------------------------------------------------");
        System.out.println( "needle: " + needle );
        System.out.println( "haystack: " + haystack );

        this.needle = needle;
        this.haystack = haystack;
        this.badCharShift = initBadCharShift();
        this.result = performSearch();
    }

    char[] initBadCharShift() {
        char[] badCharShift = new char[256];
        char[] needleChArr = needle.toCharArray();

        for (int i = 0; i < badCharShift.length; i++) {
            badCharShift[i] = (char) needleChArr.length;
        }

        for (int i = 0; i < needleChArr.length; i++) {
            _iterations++;

            badCharShift[ (int) needleChArr[i] ] = (char) ( needleChArr.length - i );
        }

        return badCharShift;
    }

    String[] performSearch() {
        List<String> result = new LinkedList<>();
        char[] needleChArr = this.needle.toCharArray();
        char[] haystackChArr = this.haystack.toCharArray();
        
        int needleIndex = needleChArr.length - 1;
            
        for (int haystackIndex = 0; haystackIndex < haystackChArr.length; haystackIndex++) {
            _iterations++;

            char haystackLetter = haystackChArr[ haystackIndex + needleIndex ];
            char needleLetter = needleChArr[ needleIndex ];
            
            // System.out.println("Needle letter: " + needleLetter + ", haystackLetter: " + haystackLetter);

            if ( isWildCard( haystackLetter ) ) {
                --needleIndex;
                continue;
            }

            if ( haystackLetter == needleLetter ) {
                System.out.println("--- haystackLetter == needleLetter ---");

                // First match found. Now find the rest...
                while ( haystackLetter == needleLetter ) {

                    System.out.println("--------------- In while loop");
                    
                    System.out.println(needleIndex);

                    if ( needleIndex <= 0 ) { // TODO: TESTING
                        System.out.println("found a match");

                        // Temporary, to figure out if the algorithm works at its most basic level
                        result.add( "FOUND A MATCH" );

                        System.out.println("About to return match");
                        return result.toArray( new String[0] );
                    }
                    
                    needleLetter = needleChArr[ --needleIndex ];
                }
            } else {
                // System.out.println("Adding " + (int)badCharShift[ haystackLetter ] + " to haystackIndex");
                needleIndex = needleChArr.length - 1;
                haystackIndex += badCharShift[ haystackLetter ];
            }
        }

        return result.toArray( new String[result.size()] );
    }

    static boolean isWildCard(char letter) {
        boolean wildcard;

        if (letter == '_') {
            wildcard = true;
        } else {
            wildcard = false;
        }
        
        return wildcard;
    }

    //
    // Getters
    //

    public String getNeedle() { return needle; }
    public String getHaystack() { return haystack; }
    public String[] getResult() { return result; }

    //
    // Overrides
    //

    @Override
    public String toString() {
        return Arrays.asList(result).toString();
    }
}
