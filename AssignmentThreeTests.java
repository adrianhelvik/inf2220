import java.util.*;

class AssignmentThreeTests {

    boolean test_search_where_wildcard_is_last()
    {
        String needle = "Hell_";
        String haystack = "-Hello";

        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        Map<Integer, String> matches = assignment.search( needle, haystack );

        return matches.size() == 1;
    }

    boolean
        test_with_craycray_amounts_of_wildcards_and_some_letters_in_needle_for_long_haystack()
    {
        String needle = "b____________________c____________________d____________________";
        String haystack = "bbb----b-------------ccc----c-------------ddd----d---------------------";

        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        Map<Integer, String> matches = assignment.search(needle, haystack);

        System.out.println("size: " + matches.size());

        return matches.size() == 4;
    }

    boolean
        IGNORE_test_that_all_characters_not_in_needle_has_correct_bad_char_shift()
    {
        String needle = "abcd";
        AssignmentThree assignment = new AssignmentThree();
        ArrayList<Integer> bcs = assignment.createBcs( needle );

        System.out.println( "Needle: " + needle );

        boolean results = true;

        for ( int ch = 0; ch < bcs.size(); ch++ ) {
            if ( needle.indexOf( ch ) == -1 ) {
                results &= bcs.get( ch ) == needle.length();
            }
        }

        return results;
    }

    boolean
        IGNORE_test_that_characters_in_needle_have_the_correct_bad_char_shift()
    {
        String needle = "abcd";
        AssignmentThree assignment = new AssignmentThree();
        ArrayList<Integer> bcs = assignment.createBcs( needle );

        System.out.println( "Needle: " + needle );

        System.out.println( "a= " + 3 + ": " + (bcs.get('a')==3) + " is: " + bcs.get('a') );
        System.out.println( "b= " + 2 + ": " + (bcs.get('b')==2) + " is: " + bcs.get('b') );
        System.out.println( "c= " + 1 + ": " + (bcs.get('c')==1) + " is: " + bcs.get('c') );
        System.out.println( "d= " + 4 + ": " + (bcs.get('d')==4) + " is: " + bcs.get('d') );
        
        boolean pass =
               bcs.get( 'a' ) == 3
            && bcs.get( 'b' ) == 2
            && bcs.get( 'c' ) == 1
            && bcs.get( 'd' ) == 4;

        return pass;
    }

    boolean
        test_if_wildcard_matching_multiple_works()
    {
        String needle = "_xx";
        String haystack = "0xx0xx0xx";
        System.out.println("needle: " + needle + ", haystack: " + haystack);

        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;
        
        Map<Integer, String> matches = assignment.search( needle, haystack );

        return matches.size() == 3;
    }

    boolean
        another_simple_search()
    {
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        Map<Integer, String> results = assignment.search( "abcd", "xxxxabcdxxxxxxxx" );

        return results.containsValue( "abcd" );
    }

    boolean
        a_simple_search()
    {
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;
        
        Map<Integer, String> results = assignment.search( "abcd", "xxxabcdxxxxxxxx" );

        return results.containsValue( "abcd" );
    }

    boolean
        test_search_where_all_needle_chars_are_wildcards()
    {
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;
        
        Map<Integer, String> results = assignment.search( "____", "abcdefgh" );

        return results.values().contains( "abcd" )
            && results.values().contains( "bcde" )
            && results.values().contains( "bcde" )
            && results.values().contains( "cdef" )
            && results.values().contains( "defg" )
            && results.values().contains( "efgh" )
            && ! results.values().contains( "xxxx" );
    }

    boolean
        test_search_with_wildcard_and_multiple_matches()
    {
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;
        
        Map<Integer, String> results = assignment.search( "_ool", "abcde cool cccccc pool f tool");

        return results.values().contains( "cool" )
            && results.values().contains( "pool" )
            && results.values().contains( "tool" );
    }
    
    boolean
        test_search_with_multiple_separated_wildcards()
    {
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;
        Map<Integer, String> results = assignment.search( "A_B_C_D", "lol man --- blablaAxBxCxDsdasdad and AoBsCdDwas" );

        System.out.println( results );

        return results.size() == 2
            && results.values().contains( "AxBxCxD" )
            && results.values().contains( "AoBsCdD" );
    }

    boolean
        test_search_with_two_wildcards()
    {

        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        Map<Integer, String> results = assignment.search( "A_B_C_D", "aAxBxCxD" );

        System.out.println( results );

        return results.size() == 1;
    }

    boolean
        test_search_with_multiple_consecutive_wildcards()
    {

        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        Map<Integer, String> results = assignment.search( "b__e", "bike and babe and bile" );

        System.out.println( results );

        return results.size() == 3
            && results.get(0).equals( "bike" )
            && results.get(9).equals( "babe" )
            && results.get(18).equals( "bile" );
    }
    
    boolean
        test_search_with_multiple_wildcards()
    {
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;
        
        Map<Integer, String> res = assignment.search( "H_l_o", "Hello-there-Heljo-man" );

        System.out.println( res );

        return res.size() == 2
            && res.get(0).equals( "Hello" )
            && res.get(12).equals( "Heljo" );
    }
    
    boolean
        simple_search_with_one_wildcard()
    {
        String needle = "A_C";
        String haystack = "aAbCde";
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        System.out.println( "Searching for " + needle + " in " + haystack );

        Map<Integer, String> matches = assignment.search( needle, haystack );
        
        System.out.println( "Result: " + matches );

        return matches.size() == 1;
    }
    
    boolean
        simple_search_with_two_matches()
    {
        String needle = "world";
        String haystack = "worldworld";
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        System.out.println( "Searching for " + needle + " in " + haystack );

        Map<Integer, String> matches = assignment.search( needle, haystack );
        
        System.out.println( "Result: " + matches );

        return matches.size() == 2;
    }

    boolean
        simple_search_with_one_match_not_at_beginning_of_haystack()
    {
        String needle = "world";
        String haystack = "hello world";
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        System.out.println( "Searching for " + needle + " in " + haystack );

        Map<Integer, String> matches = assignment.search( needle, haystack );
        
        System.out.println( "Result: " + matches );

        return matches.size() > 0;
    }
    
    boolean
        needle_and_haystack_are_equal()
    {
        String word = "hello";
        AssignmentThree assignment = new AssignmentThree();
        assignment.testPrint = true;

        System.out.println( "Searching for " + word + " in " + word);

        Map<Integer, String> matches = assignment.search( word, word );
        
        System.out.println( "Result: " + matches );

        return matches.size() > 0;
    }
    
    boolean 
        test_init_bad_char_shift_duck()
    {
        String needle = "LUCKYDUCKCLUCK";
        AssignmentThree assignment = new AssignmentThree();
        ArrayList<Integer> res = assignment.createBcs( needle );

        System.out.println( "Reference data collected from http://programmering.wiki.ifi.uio.no/Boyer-Moore" );
        System.out.println( "Needle: " + needle );
        
        boolean pass = true;

        pass &= res.get(0) == 14;
        System.out.println( "Not in needle: " + res.get(0) + " should be " + 14 + " passed so far: " + pass );
        System.out.println( "Rest:" );
        pass &= res.get('C') == 1;
        System.out.println( res.get('C') + " should be " + 1 + " passed so far: " + pass );
        pass &= res.get('D') == 8;
        System.out.println( res.get('D') + " should be " + 8 + " passed so far: " + pass );
        pass &= res.get('K') == 5;
        System.out.println( res.get('K') + " should be " + 5 + " passed so far: " + pass );
        pass &= res.get('L') == 3;
        System.out.println( res.get('L') + " should be " + 3 + " passed so far: " + pass );

        return pass;
    }

}
