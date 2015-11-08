import java.util.*;

class TestsForStringSearch {

    /*
    boolean performMoreAdvancedSearch() {
        StringSearch search = new StringSearch( "LUCKYDUCKCLUCK", "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX" );
        System.out.println( search );
        String[] searchResults = search.performSearch();
        int length = searchResults.length;

        return length > 0;
    }

    boolean performSimpleSearch() {
        String[] results = new StringSearch("e", "Hello").performSearch();
        System.out.println( Arrays.asList( results ) );
        return results.length > 0;
    }
    */
    
    boolean performBasicSearch() {
        return new StringSearch("hello", "hello").performSearch().length > 0;
    }
    
    /*
    boolean doesWildcardMatchUnderscores() {
        return StringSearch.isWildCard( '_' );
    }
    
    boolean doesWildcardNotMatchAnyOtherCharacters() {
        boolean res = true;

        for (int i = 0; i < 256; i++) {
            if ( (char) i != '_' ) {
                res &= !StringSearch.isWildCard( (char) i );
            }
        }

        return res;
    }
    */
}
