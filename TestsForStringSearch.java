import java.lang.reflect.*;
import java.util.*;

class TestsForStringSearch {

    // TODO: needleIsLongerThanHaystack

    // Current position

    //boolean testEnd() { StringSearch search = new StringSearch(); search.needle = "abcde"; search.haystack = ""; System.out.println("STUB"); return false; }
    
    boolean test_search_with_multiple_separated_wildcards2() {
        
        StringSearch search = new StringSearch( "H_l_o", "Hello-there-Heljo-man" );

        Substring[] results = search.getResults();
        System.out.println( Arrays.asList( results ) );

        return results.length == 2
            && results[0].value().equals( "Hello" )
            && results[1].value().equals( "Heljo" );
    }

    boolean test_search_with_multiple_separated_wildcards2_manual_for_debugging() {

        //
        // BUG: When a wildcard is included in the needle, 
        //

        String needle = "H_l_o";
        String haystack = "Hello-there-Heljo-man";

        StringSearch search = new StringSearch();
        search.needle = needle;
        search.haystack = haystack;
        search.init();

        while ( !search.end() ) {
            
            search.printIntermediateSearch();

            if ( search.matchFound() ) {
                System.out.println("FOUND MATCH");
                search.addResults();
            }

            search.checkForWildcard();

            search.increment();
        }

        Substring[] results = search.getResults();

        return results.length == 2
            && results[0].value().equals( "Hello" )
            && results[1].value().equals( "Heljo" );
    }

    boolean test_search_with_multiple_separated_wildcards() {

        StringSearch search = new StringSearch( "A_B_C_D", "lol man --- blablaAxBxCxDsdasdad and AoBsCdDwas" );

        Substring[] results = search.getResults();
        System.out.println( Arrays.asList( results ) );

        return results.length == 2
            && results[0].value().equals( "AxBxCxD" )
            && results[1].value().equals( "AoBsCdD" );
    }

    boolean test_search_with_multiple_consecutive_wildcards() {

        StringSearch search = new StringSearch( "b__e", "bike and babe and bile" );

        Substring[] results = search.getResults();
        System.out.println( Arrays.asList( results ) );

        return results.length == 3
            && results[0].value().equals( "bike" )
            && results[1].value().equals( "babe" )
            && results[2].value().equals( "bile" );
    }
    
    boolean test_search_with_perform_search() {
        StringSearch search = new StringSearch( "QUERY", "hei QUERY me twice QUERY. Lol"); 
        
        Substring[] results = search.getResults();
        System.out.println( Arrays.asList( results ) );
        
        return results.length == 2
            && results[0].start == 4
            && results[0].end == 9
            && results[1].start == 19
            && results[1].end == 24;
    }

    boolean test_search_with_multiple_results()
    {
        
        StringSearch search = new StringSearch();

        search.needle = "abc";
        //                    3  67  10
        search.haystack = "oooabcoabc";
        search.init();

        while ( !search.end() ) {
            search.printIntermediateSearch();
            if ( search.matchFound() ) {
                search.addResults();
                System.out.println("Found match. Results so far: " + Arrays.asList( search.getResults() ) );
            }
            search.increment();
        }

        Substring[] subs = search.getResults();
        
        boolean res = true;
        
        res &= subs[0].start == 3;
        System.out.println(res);
        res &= subs[0].end   == 6;
        System.out.println(res);
        res &= subs[1].start == 7;
        System.out.println(res);
        res &= subs[1].end   == 10;
        System.out.println(res);

        return res;
    }

    boolean test_end_in_loop() {

        StringSearch search = new StringSearch();
        search.needle = "a";
        search.haystack = "b";
        search.init();

        try {
            while ( !search.end() ) {
                search.printIntermediateSearch();

                System.out.println("Incrementing");
                search.increment();
            }

            return true;
        } catch ( Exception e ) {
            System.out.println("Exception thrown in test_end_in_loop");
            e.printStackTrace();
            return false;
        }
    }

    boolean testEndWithNeedleOfLengthThree()
    {
        StringSearch search = new StringSearch();

        search.needle = "yyy";
        search.haystack = "xxxxxxxx";
        search.init();

        for (int i = 0; search.haystackIndex() < search.haystack.length(); i++) {
            search.printIntermediateSearch();

            search.printOffsetAndCounter();

            if ( search.end() ) {
                System.out.println("search.end() was true before completion");
                return false;
            }

            search.increment();
        }
        
        search.printIntermediateSearch();

        if ( !search.end() ) {
            System.out.println("search.end() was untrue when completed");
            return false;
        }
        
        return true;
    }

    boolean testEndWithNeedleOfLengthOne()
    {

        StringSearch search = new StringSearch();

        search.needle = "y";
        search.haystack = "xxxxxx";
        search.init();

        for (int i = 0; i < search.haystack.length(); i++) {
            search.printIntermediateSearch();
            if ( search.end() ) {
                return false;
            }
            search.increment();
        }

        if ( !search.end() ) {
            return false;
        }

        return true;
    }

    boolean test_manual_wildcard_search()
    {

        //
        // BUG: When a wildcard is found, the maximum offset increment is changed for successive 
        //

        boolean result = false;

        StringSearch search = new StringSearch();

        search.needle = "ba_a";
        search.haystack = "xxbamaxx";
        search.init();

        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();

        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();
        
        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();
        
        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();
        
        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();
        
        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();
        
        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();
        search.increment();
        
        if ( search.matchFound() ) search.addResults();
        search.printIntermediateSearch();
        search.checkForWildcard();

        result = search.getResults().length > 0 && search.getResults()[0].value().equals( "bama" );
        
        return result;
    }

    boolean test_more_complex_manual_search()
    {

        //
        // BUG: When a match has been found, incrementation is 1 step too little
        //

        StringSearch search = new StringSearch();
        search.needle = "QUERY";
        search.haystack = "kkkkkQUERYssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        search.init();

        for ( int i = 0; i < 10; i++ ) {
            search.printIntermediateSearch();
            if ( search.matchFound() ) {
                search.addResults();
            }
            search.increment();
        }

        search.printResults();

        return search.getResults()[0].value().equals("QUERY");
    }

    boolean testSimpleManualSearch() 
    {

        StringSearch search = new StringSearch();

        search.needle = "abc";
        search.haystack = "abc";
        search.init();

        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();

        return search.matchFound();
    }

    boolean testLuckyDuck3rdLetter() 
    {
        boolean result = true;

        StringSearch search = new StringSearch();

        search.needle = "LUCKYDUCKCLUCK";
        search.haystack = "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX";
        search.init();

        /*
           search.printNeedleAndHaystack();
           search.printOffsetAndCounter();
           search.printIndexes();
           search.printLetters();
           */

        search.printIntermediateSearch();
        search.printLetters();
        
        search.increment();
        
        search.printIntermediateSearch();
        search.printLetters();
        
        search.increment();
        
        // Start: Matching characters

        search.printIntermediateSearch();
        search.printLetters();

        search.increment();
        
        search.printIntermediateSearch();
        search.printLetters();

        search.increment();
        
        search.printIntermediateSearch();
        search.printLetters();

        search.increment();
        
        search.printIntermediateSearch();
        search.printLetters();

        search.increment();
        
        search.printIntermediateSearch();
        search.printLetters();

        // End: Matching characters

        search.increment();
        
        search.printIntermediateSearch();
        search.printLetters();

        search.printNeedleAndHaystack();
        search.printOffsetAndCounter();
        search.printIndexes();
        search.printLetters();

        result &= search.needleLetter() == 'K';
        result &= search.haystackLetter() == 'Y';

        return result;
    }

    boolean testLuckyDuck2ndLetter() 
    {
        boolean result = true;

        StringSearch search = new StringSearch();

        search.needle = "LUCKYDUCKCLUCK";
        search.haystack = "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX";
        search.init();

        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();

        result &= search.needleLetter() == 'K';
        result &= search.haystackLetter() == 'C';

        return result;
    }

    boolean check_if_jump_is_correct_when_first_queried_letters_dont_match2() 
    {

        StringSearch search = new StringSearch();
        search.needle = "abcd";
        search.haystack = "xxxcxxxxxxxx";
        search.init();

        search.printBadCharShift();

        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();

        return search.counter == 1;
    }

    boolean check_if_jump_is_correct_when_first_queried_letters_dont_match1() 
    {

        StringSearch search = new StringSearch();
        search.needle = "abc";
        search.haystack = "xxxxxxxxxxx";
        search.init();

        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();

        return search.counter == search.needle.length();
    }

    boolean check_if_jump_is_correct_when_first_queried_letters_match() 
    {

        StringSearch search = new StringSearch();
        search.needle = "abc";
        search.haystack = "abc";
        search.init();

        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();

        return search.needleIndex() == search.haystackIndex() && search.haystackIndex() == search.needle.length() - 2;
    }

    boolean skipsSameNumberOfLettersAsLengthOfNeedleIfNoMatchAtFirst() 
    {

        StringSearch search = new StringSearch();

        search.needle = "abc";
        search.haystack = "123456789xxxxxxx";
        search.init();

        search.printIntermediateSearch();
        search.increment();
        search.printIntermediateSearch();

        return search.counter == search.needle.length();
    }

    boolean letterAtNeedlesLengthInHaystackIsFirstToBeQueried()
    {
        boolean result = true;

        StringSearch search = new StringSearch();

        search.needle = "LUCKYDUCKCLUCK";
        search.haystack = "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX";
        search.init();

        search.printNeedleAndHaystack();
        search.printOffsetAndCounter();
        search.printIndexes();
        search.printLetters();

        search.printIntermediateSearch();

        result &= search.haystackLetter() == search.haystack.charAt( search.needle.length() - 1 );

        return result;
    }

    boolean lastLetterOfNeedleIsFirstToBeQueried()
    {
        boolean result = true;

        StringSearch search = new StringSearch();

        search.needle = "LUCKYDUCKCLUCK";
        search.haystack = "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX";
        search.init();

        search.printNeedleAndHaystack();
        search.printOffsetAndCounter();
        search.printIndexes();
        search.printLetters();

        search.printIntermediateSearch();

        result &= search.needleLetter() == search.needle.charAt( search.needle.length() - 1 );

        return result;
    }

    boolean testInitBadCharShift()
    {
        StringSearch search = new StringSearch();

        search.needle = "LUCKYDUCKCLUCK";
        search.haystack = "MUCKYDUXCLUCKDUCKYCLUCKYDUCKMUCKLUCKMCLUCKYDUCKCLUCKDUXCLUCKX";

        search.init();

        System.out.println("Checking that bad char shift is ok");
        search.printBadCharShift();

        boolean result = true;

        if ( search.badCharShift[ 0 ] != 14 )
            result = false;

        if ( search.badCharShift['C'] != 1 )
            result = false;

        if ( search.badCharShift['D'] != 8 )
            result = false;

        if ( search.badCharShift['K'] != 5 )
            result = false;

        if ( search.badCharShift['L'] != 3 )
            result = false;

        return result;
    }
}
