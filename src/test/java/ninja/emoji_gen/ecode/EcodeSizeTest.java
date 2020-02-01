package ninja.emoji_gen.ecode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class EcodeSizeTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getCodeTest() {
        assertEquals("mdpi", EcodeSize.MDPI.getCode());
        assertEquals("hdpi", EcodeSize.HDPI.getCode());
        assertEquals("xhdpi", EcodeSize.XHDPI.getCode());
        assertEquals("xxhdpi", EcodeSize.XXHDPI.getCode());
    }

    @Test
    public void fromIdTest() {
        assertEquals(EcodeSize.MDPI, EcodeSize.fromId(0));
        assertEquals(EcodeSize.HDPI, EcodeSize.fromId(1));
        assertEquals(EcodeSize.XHDPI, EcodeSize.fromId(2));
        assertEquals(EcodeSize.XXHDPI, EcodeSize.fromId(3));
    }

    @Test
    public void fromIdTest_illegal() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal size ID 100");

        EcodeSize.fromId(100);
    }

    @Test
    public void fromCodeTest() {
        assertEquals(EcodeSize.MDPI, EcodeSize.fromCode("mdpi"));
        assertEquals(EcodeSize.MDPI, EcodeSize.fromCode("Mdpi"));
        assertEquals(EcodeSize.MDPI, EcodeSize.fromCode("MDPI"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromCodeTest_unknown() {
        EcodeSize.fromCode("unknown");
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ConstantConditions")
    public void fromCodeTest_null() {
        EcodeSize.fromCode(null);
    }
}
