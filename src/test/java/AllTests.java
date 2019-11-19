import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        ArraysCompareTest.class,
        QuickBeforeAfterTest.class,
        StringHelperParameterizedTest.class,
        StringHelperTest.class
})
public class AllTests {
}
