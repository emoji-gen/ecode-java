package ninja.emoji_gen.ecode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class EcodeFormatTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void fromIdTest() {
        assertEquals(EcodeFormat.PNG, EcodeFormat.fromId(0));
        assertEquals(EcodeFormat.WEBP, EcodeFormat.fromId(1));
    }

    @Test
    public void fromIdTest_illegal() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal format ID 100");

        EcodeFormat.fromId(100);
    }

    @Test
    public void fromCodeTest_png() {
        assertEquals(EcodeFormat.PNG, EcodeFormat.fromCode("png"));
        assertEquals(EcodeFormat.PNG, EcodeFormat.fromCode("Png"));
        assertEquals(EcodeFormat.PNG, EcodeFormat.fromCode("PNG"));
    }

    @Test
    public void getIdTest() {
        assertEquals(0, EcodeFormat.PNG.getId());
        assertEquals(1, EcodeFormat.WEBP.getId());
    }
}
