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

    @Override
    public String toString() {
        return "Substring \"" + string + "\"[" + start + "," + end + "]: \"" + string.substring( start, end ) + "\"";
    }
}
