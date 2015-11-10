import java.util.*;

class AssignmentThreeTests {

    boolean testInitBadCharShift()
    {
        ArrayList<Integer> res = AssignmentThree.createBcs( "LUCKYDUCKCLUCK" );

        System.out.println( "Data collected from http://programmering.wiki.ifi.uio.no/Boyer-Moore" );
        
        boolean pass = true;

        pass &= res.get(0) == 14;
        System.out.println( "Not in needle: " + res.get(0) + " == " + 14 + " passed so far: " + pass );
        System.out.println( "Rest:" );
        pass &= res.get('C') == 1;
        System.out.println( res.get('C') + " == " + 1 + " passed so far: " + pass );
        pass &= res.get('D') == 8;
        System.out.println( res.get('D') + " == " + 8 + " passed so far: " + pass );
        pass &= res.get('K') == 5;
        System.out.println( res.get('K') + " == " + 5 + " passed so far: " + pass );
        pass &= res.get('L') == 3;
        System.out.println( res.get('L') + " == " + 3 + " passed so far: " + pass );

        return pass;
    }

    boolean test_search_2()
    {
        Map<Integer, String> results = AssignmentThree.search( "____", "abcdefgh");

        return results.values().contains( "abcd" )
            && results.values().contains( "bcde" )
            && results.values().contains( "bcde" )
            && results.values().contains( "cdef" )
            && results.values().contains( "defg" )
            && results.values().contains( "efgh" )
            && ! results.values().contains( "xxxx" );
    }

    boolean test_search_1()
    {
        Map<Integer, String> results = AssignmentThree.search( "_ool", "abcde cool cccccc pool f tool");

        return results.values().contains( "cool" )
            && results.values().contains( "pool" )
            && results.values().contains( "tool" );
    }
    
    boolean test_search_with_multiple_separated_wildcards()
    {

        Map<Integer, String> results = AssignmentThree.search( "A_B_C_D", "lol man --- blablaAxBxCxDsdasdad and AoBsCdDwas" );

        System.out.println( results );

        return results.size() == 2
            && results.values().contains( "AxBxCxD" )
            && results.values().contains( "AoBsCdD" );
    }

    boolean test_search_with_multiple_consecutive_wildcards()
    {

        Map<Integer, String> results = AssignmentThree.search( "b__e", "bike and babe and bile" );

        System.out.println( results );

        return results.size() == 3
            && results.get(0).equals( "bike" )
            && results.get(9).equals( "babe" )
            && results.get(18).equals( "bile" );
    }
    
    boolean test_search_with_multiple_wildcards()
    {
        
        Map<Integer, String> res = AssignmentThree.search( "H_l_o", "Hello-there-Heljo-man" );

        System.out.println( res );

        return res.size() == 2
            && res.get(0).equals( "Hello" )
            && res.get(12).equals( "Heljo" );
    }

    boolean needle_and_haystack_are_equal()
    {
        return AssignmentThree.search( "hello", "hello" ).size() > 0;
    }
}
