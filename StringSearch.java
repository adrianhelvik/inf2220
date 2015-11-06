import java.util.*;

public class StringSearch {

    int _iterations = 0;

    char[] badCharShift;
    String needle;
    String haystack;
    String[] result;

    public StringSearch( String needle, String haystack ) {
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

        for (int i = 0; i < needleChArr.length; i++) {
            _iterations++;

            badCharShift[ (int) needleChArr[i] ] = (char) i;
        }

        return badCharShift;
    }

    String[] performSearch() {
        List<String> result = new LinkedList<>();
        char[] haystackArr = this.haystack.toCharArray();
        
        // Reverse iterate through haystack
        for (int i = haystackArr.length - 1; i >= 0; i--) {
            _iterations++;

            char haystackLetter = haystackArr[i];
            if ( isWildCard( haystackLetter ) ) {
                continue;
            }
            
            while ( badCharShift[ haystackLetter ] != 0 )
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

    @Override
    public String toString() {
        return Arrays.asList(result).toString();
    }
}
