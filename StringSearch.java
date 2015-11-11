import java.util.*;

public class StringSearch {

    //
    // Attributes
    // 

    int CHAR_MAX = 256;
    final char WILDCARD = '_';
    char[] needle;
    char[] haystack;
    int[] bcs;

    boolean testing = false;

    //
    // Constructors
    //

    public StringSearch(String needle, String haystack) {
        this.needle = needle.toCharArray();
        this.haystack = haystack.toCharArray();
        this.bcs = createBcs( this.needle );
    }

    //
    // Getters
    //

    int[] getBcs() { return bcs; }

    // 
    // Misc.
    // 

    /**
     * Create bad char shift array for a given search query.
     * Tested.
     */
    int[] createBcs(char[] needle) {
        int[] bcs = new int[CHAR_MAX];

        bcs['_'] = nlen();

        Set<Character> inNeedle = needleSet();

        // Go through letters of needle
        for (int i = 0; i < nlen(); i++) {
            
            if (last() - i != 0)
                bcs[needle[i]] = last() - i;
        }

        // Go through all possible letters
        for (int i = 0; i < CHAR_MAX; i++) {

            if (! inNeedle.contains((char)i) || bcs[i] == 0)
                bcs[i] = bcs['_'];
        }

        return bcs;
    }

    Set<Character> needleSet() {
        Set<Character> inNeedle = new HashSet<>();
        
        for (char ch : needle) {
            inNeedle.add(ch);
        }

        return inNeedle;
    }

    int countWildcardsStart(char[] needle) {
        int cnt = 0;

        for (char ch : needle) {
            if ( ch == WILDCARD ) {
                cnt++;
            }
            else break;
        }
        
        return cnt;
    }

    List<Integer> search() {

        List<Integer> results = new LinkedList<>();

        int base = 0;
        int offset = last();

        while (!endReached(base)) {
            
            printIntermediateSearch(base,offset);

            if (letterMatch(base, offset)) {
                while (letterMatch(base, offset)) {
                    printIntermediateSearch(base,offset);

                    offset--;
                }

                // Undo extra offset
                offset++;

                if (offset == 0) {
                    System.out.println("Added " + base);
                    results.add(base);
                }
                
                offset = last();
            }

            base += bcs[haystack[base+offset]];
        }

        return results;
    }

    Map<Integer, String> searchAndMap() {
        List<Integer> indexes = search();
        Map<Integer, String> result = new HashMap<>();

        String haystack = "";

        for (char ch : this.haystack)
            haystack += ch;

        for (int index : indexes)
            result.put(index, haystack.substring(index, index + needle.length));

        return result;
    }

    //
    // Helpers
    //

    int nlen() { return needle.length; }
    int hlen() { return haystack.length; }
    int last() { return nlen() - 1; }
    
    /**
     * Verify that the end of a search has been reached based on its base number.
     * Tested
     */
    boolean endReached(int base) {
        return base + last() >= hlen() || needle.length == 0;
    }

    /**
     * Index of selected needle letter.
     */
    int needleIndex(int base, int offset) {
        return offset;
    }

    /**
     * Index of selected haystack letter.
     */
    int haystackIndex(int base, int offset) {
        return base + offset;
    }

    /**
     * Check if selected letter in needle and haystack are equal.
     * Tested
     */
    boolean letterMatch(int base, int offset) {
        
        if (needleIndex(base,offset) >= needle.length || haystackIndex(base,offset) >= haystack.length || needleIndex(base,offset) < 0 || haystackIndex(base,offset) < 0) {
            return false;
        }

        char n = needle[needleIndex(base,offset)];
        char h = haystack[haystackIndex(base,offset)];

        return n == h || n == WILDCARD;
    }

    //
    // Test printing
    //
    void printIntermediateSearch( int base, int offset ) {

        if (! testing)
            return;

        String haystack = ""; for (int i = 0; i < this.haystack.length; i++) haystack += this.haystack[i];
        String needle = ""; for (int i = 0; i < this.needle.length; i++) needle += this.needle[i];


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
}
