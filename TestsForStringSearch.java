import java.util.*;

class TestsForStringSearch {
    /*
    boolean performWildcardSearchWithNoMatch() {
        String needle;
        String haystack;

        needle = "hell_ world";
        haystack = "Hello there... How'd'ya do?";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < res.length; i++) {
            System.out.println("     " + res[i]);
        }

        return res.length == 0;
    }

    boolean performSearchWithNoMatch() {
        String needle;
        String haystack;

        needle = "hello world";
        haystack = "Hello there... How'd'ya do?";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        return res.length == 0;
    }
    
    boolean performSearchWhereNeedleIsSubstringOfHaystack() {
        String needle;
        String haystack;

        needle = "sub";
        haystack = "aaaaaaaaaaaaaasubaaaaaaaaaaa";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        return res[0].value().equals("sub");
    }

    boolean performSearchWhereNeedleAndHaystackAreTheSame() {
        String needle;
        String haystack;

        needle = "abcd";
        haystack = "abcd";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        return res.length > 0 && res[0].value().equals("abcd");
    }
    
    boolean performSearchWithOneLetterInNeedleAndHaystackMatching() {
        String needle;
        String haystack;

        needle = "a";
        haystack = "a";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        return res.length == 1;
    }
    
    boolean performSimpleSearchWithEmptyHaystackAndNoMatch() {
        String needle;
        String haystack;

        needle = "a";
        haystack = "";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        return res.length == 0;
    }

    boolean performSearchWithWildcard() {
        String needle;
        String haystack;

        needle = "hell_ world";
        haystack = "Hello there... hella world. How'd'ya do?";

        Substring[] res = new StringSearch( needle, haystack ).performSearch();

        System.out.println( Arrays.asList( res ) );

        return res.length > 0;
    }
    
    boolean performMoreAdvancedSearch() {
        StringSearch search = new StringSearch( "LUCKYDUCKCLUCK", "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX" );
        Substring[] searchResults = search.performSearch();
        int length = searchResults.length;

        return length > 0;
    }
    */
    
    boolean performSimpleSearch() {
        Substring[] results = new StringSearch("e", "Hello").getResult();
        return results.length > 0;
    }
    
    boolean needleIsStartOfHaystack() {
        Substring[] results = new StringSearch("Hel", "Hello").getResult();
        return results.length > 0;
    }
    
    boolean needleAndHaystackAreEqual() {
        return new StringSearch("hello", "hello").getResult().length > 0;
    }
    
    boolean oneLetterNeedleAndHaystackAreEqual() {
        return new StringSearch("a", "a").getResult().length > 0;
    }
    
    boolean doesWildcardMatchUnderscores() {
        return StringSearch.isWildCard( '_' );
    }
    
    boolean doesWildcardNotMatchAnyOtherCharacters() {
        boolean res = true;

        for (int i = 0; i < 256; i++) {
            if ( ( char ) i != '_' ) {
                res &= !StringSearch.isWildCard( (char) i );
            }
        }

        return res;
    }
}
