package ninja.emoji_gen.ecode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("ConstantConditions")
public class EcodeBuilderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void textTest_null() {
        expectedException.expect(NullPointerException.class);
        new EcodeBuilder().text(null);
    }

    @Test
    public void textTest_empty() {
        expectedException.expect(IllegalArgumentException.class);
        new EcodeBuilder().text("");
    }

    @Test
    public void buildTest_nullText() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("`text` is required");
        new EcodeBuilder().build();
    }
}
