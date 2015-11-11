import java.util.*;

public class TestStringSearch {

    //
    // Tests (starting from top)
    // Ignore a tests by prepending IGNORE to the method name
    //
    // When chaining is-calls, I use & to avoid short curcuiting the call
    //

    boolean
        test_program_execution()
    {
        AssignmentThree.main(new String[] {"sampleNeedle.txt", "sampleHaystack.txt", "test"});

        System.out.println( AssignmentThree._testResults );

        return is(AssignmentThree._testResults.get(0), "hello", "Ran program with testing enabled and the result should contain that")
            & is(AssignmentThree._testResults.get(15), "halla", "Ran program with testing enabled and the result should contain that");
    }

    boolean
        test_multiple_results_for_wildcard()
    {
        String needle = "hel_o";
        String haystack = "hello-world-hello-heljo";

        StringSearch search = new StringSearch(needle,haystack);
        Map<Integer, String> results = search.searchAndMap();

        System.out.println(results);

        return is(results.size(), 3, "size of results")
            & is(results.get(0), "hello", "should contain value")
            & is(results.get(12), "hello", "should contain value")
            & is(results.get(18), "heljo", "should contain value");
    }

    boolean
        test_if_empty_needle_and_empty_haystack_produces_empty_result()
    {
        String needle = "";
        String haystack = "";

        StringSearch search = new StringSearch(needle,haystack);
        List<Integer> res = search.search();

        return is(res.size(), 0, "There can be no matches");
    }

    boolean
        test_if_empty_haystack_produces_empty_result()
    {
        String needle = "abc";
        String haystack = "";

        StringSearch search = new StringSearch(needle,haystack);
        List<Integer> res = search.search();

        return is(res.size(), 0, "There can be no matches");
    }

    boolean
        test_if_empty_needle_produces_empty_result()
    {
        String needle = "";
        String haystack = "asasb";

        StringSearch search = new StringSearch(needle,haystack);
        List<Integer> res = search.search();

        return is(res.size(), 0, "There can be no matches");
    }

    boolean
        test_if_larger_needle_than_haystack_produces_empty_result()
    {
        String needle = "aaaaaaaaaaa";
        String haystack = "b";

        StringSearch search = new StringSearch(needle,haystack);
        List<Integer> res = search.search();

        return is(res.size(), 0, "There can be no matches");
    }

    boolean
        test_if_wildcard_matching_multiple_works()
    {
        String needle = "_xx";
        String haystack = "0xx0xx0xx";
        System.out.println("needle: " + needle + ", haystack: " + haystack);

        StringSearch search = new StringSearch(needle,haystack);
        
        List<Integer> matches = search.search();

        return is(matches.size(), 3, "number of matches")
            & is(matches.get(0), 0)
            & is(matches.get(1), 3)
            & is(matches.get(2), 6);
    }
    
    boolean
        test_search_with_multiple_wildcards()
    {
        String haystack = "Hello-there-Heljo-man";
        StringSearch search = new StringSearch("H_l_o", haystack);
        
        List<Integer> res = search.search();

        System.out.println( res );

        return is(res.size(), 2, "that's the number of results")
            & is(res.get(0), haystack.indexOf("Hello"), "that should be the first result")
            & is(res.get(1), haystack.indexOf("Heljo"), "that should be the 2nd result");
    }
    
    boolean
        simple_search_with_one_wildcard()
    {
        String needle = "A_C";
        String haystack = "aAbCde";
        
        StringSearch search = new StringSearch(needle,haystack);

        System.out.println( "Searching for " + needle + " in " + haystack );

        List<Integer> matches = search.search();
        
        System.out.println( "Result: " + matches );

        return is(matches.size(), 1);
    }

    boolean
        test_simple_search_starting_with_wildcards()
    {
        String needle = "__C";
        String haystack = "aAbCde";
        
        StringSearch search = new StringSearch(needle,haystack);

        System.out.println( "Searching for " + needle + " in " + haystack );

        List<Integer> matches = search.search();
        
        System.out.println( "Result: " + matches );

        return is(matches.size(), 1);
    }
    
    boolean
        simple_search_with_two_matches()
    {
        String needle = "world";
        String haystack = "worldworld";

        StringSearch search = new StringSearch(needle, haystack);

        System.out.println( "Searching for " + needle + " in " + haystack );

        List<Integer> matches = search.search();
        
        System.out.println( "Result: " + matches );

        return is(matches.size(), 2);
    }
    
    boolean
        simple_search_with_one_match_not_at_beginning_of_haystack()
    {
        String needle = "world";
        String haystack = "hello world";
        
        StringSearch search = new StringSearch(needle,haystack);

        System.out.println( "Searching for " + needle + " in " + haystack );

        List<Integer> matches = search.search();
        
        System.out.println( "Result: " + matches );

        return is(matches.size(), 1)
            & is(matches.get(0), haystack.indexOf("world"));
    }
    
    boolean
        needle_and_haystack_are_equal()
    {
        StringSearch search = new StringSearch("hello", "hello");
        List<Integer> matches = search.search();

        System.out.println("Matches: " + matches);

        return is(matches.size(), 1);
    }

    boolean
        test_letter_match_method()
    {
        StringSearch search = new StringSearch("abc", "ibc");

        return is(search.letterMatch(0,0), false, "a and i is not equal (base=0, offset=0)")
            & is(search.letterMatch(0,1), true, "b and b are equal (base=0, offset=1)")
            & is(search.letterMatch(0,2), true, "c and c are equal (base=0, offset=2)")
            & is(search.letterMatch(1,0), false, "a and i are not equal")
            & is(search.letterMatch(2,0), false, "c and i are not equal")
            & is(search.letterMatch(2222, 0), false, "Generated indexes will be out of range");
    }

    boolean
        test_haystack_index_method()
    {
        StringSearch search = new StringSearch("Hello", "world");

        return is(search.haystackIndex(0,0), 0)
            & is(search.haystackIndex(0,1), 1)
            & is(search.haystackIndex(1,0), 1)
            & is(search.haystackIndex(1,1), 2);
    }

    boolean
        test_needle_index_method()
    {
        StringSearch search = new StringSearch("Hello", "world");

        return is(search.needleIndex(0,0), 0)
            & is(search.needleIndex(0,1), 1)
            & is(search.needleIndex(1,0), 0);
    }

    boolean
        test_end_reached_method()
    {
        StringSearch search = new StringSearch("hello", "hello");

        return is(search.endReached(0), false) && is(search.endReached(1), true);

    }
    
    boolean 
        test_init_bad_char_shift_duck()
    {
        System.out.println( "Reference data collected from http://programmering.wiki.ifi.uio.no/Boyer-Moore" );
        
        String needle = "LUCKYDUCKCLUCK";
        p( "Needle: " + needle );
        StringSearch search = new StringSearch(needle, needle);

        int[] bcs = search.getBcs();

        char[] n = needle.toCharArray();

        return is(bcs[0], 14)
            & is(bcs['C'], 1, "letter is C")
            & is(bcs['D'], 8, "letter is D")
            & is(bcs['K'], 5, "letter is K")
            & is(bcs['L'], 3, "letter is L");
    }

    //
    // Helpers
    //

    void p(Object... args) { for (Object o : args) System.out.print(o + " "); System.out.println(); }
    
    boolean is(int a, int b) {
        System.out.println(a + " should be " + b);
        if (a != b) System.out.println( ".. but is not." );
        return a == b;
    }
    
    boolean is(int a, int b, String msg) {
        System.out.println(a + " should be " + b + " because: " + msg);
        if (a != b) System.out.println( ".. but is not." );
        return a == b;
    }
    
    boolean is(boolean a, boolean b) {
        System.out.println(a + " should be " + b);
        if (a != b) System.out.println( ".. but is not." );
        return a == b;
    }

    boolean is(boolean a, boolean b, String msg) {
        System.out.println(a + " should be " + b + " because: " + msg);
        if (a != b) System.out.println( ".. but is not." );
        return a == b;
    }

    boolean is(String a, String b) {
        System.out.println(a + " should be " + b);
        boolean match = a.equals(b);
        if (! match) System.out.println( ".. but is not." );
        return match;
    }
    
    boolean is(String a, String b, String msg) {
        System.out.println("\"" + a + "\" should be \"" + b + "\" because: " + msg);
        boolean match = a.equals(b);
        if (! match) System.out.println( ".. but is not." );
        return match;
    }
}
