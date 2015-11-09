import java.util.*;

public class StringSearch {

    int _iterations = 0;
    int _bcsIterations = 0;

    int[] badCharShift;
    String needle;
    String haystack;
    List<Substring> result;

    int counter;
    int offset;

    public StringSearch( String needle, String haystack ) {

        this.needle = needle;
        this.haystack = haystack;
        this.badCharShift = initBadCharShift();
        this.result = new LinkedList<>();
        
        performSearch(); // populates result
    }

    int[] initBadCharShift() {
        this.badCharShift = new int[256];
        
        for (int i = 0; i < badCharShift.length; i++) {
            _iterations++;
            _bcsIterations++;
            badCharShift[i] = (char) ( needle.length() );
        }

        for (int i = 0; i < needle.length(); i++) {
            _iterations++;
            _bcsIterations++;

            int jumpLength = i + 1;
            char ch = needle.charAt(i);

            setJumpLength( ch, jumpLength );
        }

        printJumpLengths();
        new Scanner(System.in).nextLine();

        return badCharShift;
    }

    void setJumpLength( char ch, int jumpLength ) {

        if (badCharShift[ ch ] > jumpLength )
            badCharShift[ ch ] = (char) jumpLength;
    }

    void printJumpLengths() {
        System.out.print("Jump lengths: ");
        for (int i = 0; i < needle.length(); i++) {
            System.out.print( needle.charAt(i) + ":" + badCharShift[ needle.charAt(i) ] + " ");
        }
        System.out.println("Other: " + badCharShift[0]);
    }

    private void performSearch() {

        initCounter();
        initOffset();

        while ( !end() ) {

            // System.out.println("needle letter: '" + needleChar() + "' haystack letter: '" + haystackChar() + "'" );

            if ( matchFound() )
                addResult();

            increment();
            
            new Scanner(System.in).nextLine();
        }
    }

    void initCounter() {
        this.counter = 0;
    }

    void initOffset() {
        this.offset = needle.length() - 1;
    }

    void increment() {
        System.out.println("Incrementing...");
        System.out.println("... offset=" + offset);
        System.out.println("... counter=" + counter);
        
        if ( letterMatch() && ! matchFound() ) {
            nextNeedleLetter();
        } else if ( noMoreNeedleLetters() ) {
            nextHaystackSegment();
        } else {
            throw new RuntimeException( "SHOULD NEVER HAPPEN!" );
        }
        
        System.out.println("... offset=" + offset);
        System.out.println("... counter=" + counter);
    }

    void nextHaystackSegment() {
        counter += badCharShift[ needle.charAt( offset ) ];
    }

    void nextNeedleLetter() {
        offset--;
    }

    void resetOffset() {
        this.offset = needle.length() - 1;
    }

    boolean noMoreNeedleLetters() {
        return offset == 0;
    }

    boolean letterMatch() {
        System.out.println("Letters matched: " + needleChar());
        return needleChar() == haystackChar();
    }

    char needleChar() {
        return needle.charAt( offset );
    }

    char haystackChar() {
        return haystack.charAt( counter + offset );
    }
    
    void addResult() {
        result.add( new Substring( haystack, counter, needle.length() ) );
    }

    boolean matchFound() {
        return offset == 0 && needle.charAt( offset ) == haystack.charAt( counter + offset );
    }

    boolean end() {
        boolean result = ( offset == needle.length() - 1 && counter >= haystack.length() - needle.length() + 1 );

        System.out.println(" is end reached? " + result);

        return result;
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
    public Substring[] getResult() { return result.toArray( new Substring[0] ); }

    //
    // Overrides
    //

    @Override
    public String toString() {
        return Arrays.asList(result).toString();
    }
}
