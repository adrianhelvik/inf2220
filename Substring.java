public class Substring {
    private final String string;
    public final int start;
    public final int end;

    public Substring ( String string, int start, int end ) {
        this.start = start;
        this.end = end;
        this.string = string;

        string.substring( start, end ); // will cause any exception that might happen
    }

    public String value() {
        return string.substring( start, end );
    }

    private String shortenString( String s, int len ) {
        String res = "";

        for ( int i = 0; i < len - 3; i++ ) {
            res += s.charAt(i);
        }

        res += "...";

        return res;
    }

    @Override
    public String toString() {
        return "Substring \"" + shortenString( string, 10 ) + "\"[" + start + "," + end + "]: \"" + string.substring( start, end ) + "\"";
    }
}
