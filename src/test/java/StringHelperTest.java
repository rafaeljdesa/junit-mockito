import static org.junit.Assert.*;

import helper.StringHelper;
import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {

    private StringHelper helper;

    @Before
    public void setup() {
        helper = new StringHelper();
    }

    @Test
    public void testTruncateAInFirst2Positions_Ainfirst2Positions() {

        //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
        assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
    }

    @Test
    public void testTruncateAInFirst2Positions_AinFirstPosition() {

        //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
        assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenario() {

        assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_BasicPositiveScenario() {

        assertTrue(helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }

}
