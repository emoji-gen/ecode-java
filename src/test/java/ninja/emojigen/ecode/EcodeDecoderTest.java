package ninja.emojigen.ecode;

import org.junit.Test;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;

public class EcodeDecoderTest {
    private static final EcodeDecoder ECODE_DECODER = new EcodeDecoder();

    @Test
    public void decodeV1Test() {
        final EcodeV1 ecodeV1 = ECODE_DECODER.decodeV1("BA0hzxI0VniavN7wYWIKYw");
        assertEquals(1, ecodeV1.getVersion());
        assertEquals(EcodeLocale.EN, ecodeV1.getLocale());
        assertEquals(EnumSet.of(EcodeFlag.SIZE_FIXED, EcodeFlag.STRETCH), ecodeV1.getFlags());
        assertEquals(EcodeAlign.CENTER, ecodeV1.getAlign());
        assertEquals(EcodeSize.XHDPI, ecodeV1.getSize());
        assertEquals(EcodeFormat.WEBP, ecodeV1.getFormat());
        assertEquals(0b1100_1111, ecodeV1.getFontId());
        assertEquals(0x12345678, ecodeV1.getForegroundColor());
        assertEquals(0x9abcdef0, ecodeV1.getBackgroundColor());
        assertEquals("ab\nc", ecodeV1.getText());
    }
}
