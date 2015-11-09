import java.util.*;

public class StringSearch {

    int _iterations = 0;
    int _bcsIterations = 0;

    int[] badCharShift;
    String needle;
    String haystack;
    List<Substring> results;
    boolean wildcardEncountered = false;
    int counterAtWildcardEncounter = -1;
    int wildcardPosition;

    int counter;
    int offset;

    public StringSearch( String needle, String haystack ) {

        this.needle = needle;
        this.haystack = haystack;
        
        init(); // set badCharShift, results, counter and offset

        performSearch(); // populates results
    }
    
    // For testing purposes
    StringSearch() { }

    void init() {
        initBadCharShift();
        
        this.results = new LinkedList<>();
        initCounter();
        resetOffset();
    }

    private void performSearch() {

        while ( !end() ) {

            if ( matchFound() )
                addResults();

            checkForWildcard();

            increment();
            
        }
    }

    void checkForWildcard() {
        if ( wildcard() ) {
            wildcardEncountered = true;
            wildcardPosition = offset;
        }
    }

    boolean matchFound() {
        return offset == 0 && letterMatch();
    }

    void addResults() {
        results.add( new Substring( haystack, counter, counter + needle.length() ) );
    }

    int getJumpLength( char ch ) {
        if ( ch == '_' ) {
            throw new RuntimeException( "Wild cards do not have jump lengths!" );
        }

        else {
            return badCharShift[ ch ];
        }
    }

    void initCounter() {
        this.counter = 0;
    }

    //
    // Tested
    //

    void initBadCharShift() {
        this.badCharShift = new int[256];
        
        for (int i = 0; i < badCharShift.length; i++) {
            badCharShift[i] = (char) ( needle.length() );
        }

        for (int i = needle.length() - 1; i >= 0; i--) {

            char ch = needle.charAt(i);
            int jumpLength = needle.length() - 1 - i;

            setJumpLength( ch, jumpLength );
        }
    }

    // tested with bad char shift
    void setJumpLength( char ch, int jumpLength ) {
        if ( jumpLength < badCharShift[ ch ] && jumpLength != 0 )
            badCharShift[ ch ] = jumpLength;
    }

    boolean end() {
        return haystackIndex() >= haystack.length();
    }

    //
    // Getters
    //

    public String getNeedle() { return needle; }
    public String getHaystack() { return haystack; }
    public Substring[] getResults() { return results.toArray( new Substring[0] ); }
    int needleIndex() { return offset; }
    char needleLetter() { return needle.charAt( needleIndex() ); }
    int haystackIndex() { return counter + offset; }
    char haystackLetter() { return haystack.charAt( haystackIndex() ); }

    //
    // Overrides
    //

    @Override
    public String toString() {
        return Arrays.asList(results).toString();
    }
    
    //
    // Helpers
    //
    
    boolean letterMatch() {
        return ( needleLetter() == haystackLetter() ) || wildcard();
    }

    void increment() {

        if ( letterMatch() && ! matchFound() ) {

            nextNeedleLetter();
        } else {
            
            nextHaystackSegment();
        }
    }

    void nextHaystackSegment() {
        resetOffset();

        if ( wildcardEncountered ) {
            counter += wildcardPosition;
            wildcardEncountered = false;
        }
        else {
            if ( haystackIndex() < haystack.length() ) {
                System.out.println( counter );
                counter += badCharShift[ haystackLetter() ];
            }
        }
    }

    void nextNeedleLetter() {
        offset--;
    }

    void resetOffset() {
        offset = needle.length() - 1;
    }

    boolean noMoreNeedleLetters() {
        return offset == 0;
    }

    boolean counterOverflow() {
        boolean counterOverflow = counter > counterMax();
        return counterOverflow;
    }

    int counterMax() {
        return haystack.length() - needle.length();
    }

    boolean offsetAtStart() {
        boolean offsetStart = offset == needle.length() - 1;
        return offsetStart;
    }

    boolean wildcard() {
        boolean wildcard = needleLetter() == '_';
        return needleLetter() == '_';
    }

    //
    // Test printing
    //

    void printNeedleAndHaystack() {
        System.out.println("needle=\"" + needle + "\" haystack=\"" + haystack + "\"");
    }

    void printLetters() {
        System.out.println("n=" + needleLetter() + " h=" + haystackLetter());
    }

    void printOffsetAndCounter() {
        System.out.println("offset=" + offset + " counter=" + counter);
    }

    void printIndexes() {
        System.out.println("ni=" + needleIndex() + " hi=" + haystackIndex());
    }

    void printBadCharShift() {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < needle.length(); i++) {
            map.put(needle.charAt(i), badCharShift[ needle.charAt(i) ]);
        }

        System.out.print("Bad char shift: ");
        for (char ch : map.keySet()) {
            System.out.print(ch + "->" + map.get(ch) + " ");
        }

        System.out.println("other->" + badCharShift[0]);
        System.out.println();
    }


    void printIntermediateSearch() {

        String res = "";
        
        res += haystack + "\n";
        
        for (int i = 0; i < haystackIndex(); i++) {
            res += " ";
        }

        res += ".\n";

        for (int i = 0; i < counter; i++) {
            res += " ";
        }

        res += needle + "\n";
        
        System.out.println( res );
    }

    void printResults() {
        String res = "";

        res = "results=";

        if (results == null)
            res += null;
        else {
            res += Arrays.asList(results);
        }

        System.out.println(res);
    }
}
