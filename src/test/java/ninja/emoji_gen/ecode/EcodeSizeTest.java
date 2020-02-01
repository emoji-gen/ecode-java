package ninja.emoji_gen.ecode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EcodeSizeTest {
    @Test
    public void getCodeTest() {
        assertEquals("mdpi", EcodeSize.MDPI.getCode());
        assertEquals("hdpi", EcodeSize.HDPI.getCode());
        assertEquals("xhdpi", EcodeSize.XHDPI.getCode());
        assertEquals("xxhdpi", EcodeSize.XXHDPI.getCode());
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
