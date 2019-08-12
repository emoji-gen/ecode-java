package ninja.emojigen.ecode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("ConstantConditions")
public class EcodeV1BuilderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void textTest_null() {
        expectedException.expect(NullPointerException.class);
        new EcodeV1Builder().text(null);
    }

    @Test
    public void textTest_empty() {
        expectedException.expect(IllegalArgumentException.class);
        new EcodeV1Builder().text("");
    }

    @Test
    public void buildTest_nullText() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("`text` is required");
        new EcodeV1Builder().build();
    }
}
