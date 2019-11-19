import helper.StringHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

    private StringHelper helper;
    private String input;
    private String expectedOutputs;

    @Parameters
    public static Collection<String[]> testConditions() {
        String expectedOuputs[][] = { {"AACD", "CD"}, {"ACD", "CD"} };
        return Arrays.asList(expectedOuputs);
    }

    public StringHelperParameterizedTest(String input, String expectedOutputs) {
        this.input = input;
        this.expectedOutputs = expectedOutputs;
    }

    @Before
    public void setup() {
        helper = new StringHelper();
    }



    @Test
    public void testTruncateAInFirst2Positions_Ainfirst2Positions() {

        //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
        assertEquals(expectedOutputs, helper.truncateAInFirst2Positions(input));
    }

    @Test
    public void testTruncateAInFirst2Positions_AinFirstPosition() {

        //AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
        assertEquals(expectedOutputs, helper.truncateAInFirst2Positions(input));
    }

}
