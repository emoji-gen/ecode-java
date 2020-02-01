package ninja.emoji_gen.ecode;

import org.apache.commons.codec.binary.Base64;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;

public class EcodeDecoderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final EcodeDecoder ECODE_DECODER = new EcodeDecoder();

    @Test
    public void decodeTest() {
        Ecode ecode = ECODE_DECODER.decode("BA0hzxI0VniavN7wYWIKYw");
        assertEquals(1, ecode.getVersion());
        assertEquals(EcodeLocale.EN, ecode.getLocale());
        assertEquals(EnumSet.of(EcodeFlag.SIZE_FIXED, EcodeFlag.STRETCH), ecode.getFlags());
        assertEquals(EcodeAlign.CENTER, ecode.getAlign());
        assertEquals(EcodeSize.XHDPI, ecode.getSize());
        assertEquals(EcodeFormat.WEBP, ecode.getFormat());
        assertEquals(0b1100_1111, ecode.getFontId());
        assertEquals(0x12345678, ecode.getForegroundColor());
        assertEquals(0x9abcdef0, ecode.getBackgroundColor());
        assertEquals("ab\nc", ecode.getText());
    }

    @Test
    public void decodeTest_illegalLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal byte length 12.");

        byte[] bytes = new byte[12];
        String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decode(ecode);
    }

    @Test
    public void decodeTest_illegalAlign() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal align ID 3.");

        byte[] bytes = new byte[13];
        bytes[1] |= 0x03;

        String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decode(ecode);
    }

    @Test
    public void decodeTest_illegalSize() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal size ID 15.");

        byte[] bytes = new byte[13];
        bytes[2] |= 0xf0;

        String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decode(ecode);
    }

    @Test
    public void decodeTest_illegalFormat() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal format ID 15.");

        byte[] bytes = new byte[13];
        bytes[2] |= 0x0f;

        String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decode(ecode);
    }

    @Test
    public void validateVersionTest() {
        ECODE_DECODER.validateVersion((byte) 0b0000_0000);
    }

    @Test
    public void validateVersionTest_illegalVersion() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal ecode version 16.");

        ECODE_DECODER.validateVersion((byte) 0b1111_0000);
    }
}
