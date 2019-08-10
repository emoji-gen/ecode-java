package ninja.emojigen.ecode;

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

    @Test
    public void decodeV1Test_illegalLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal byte length 12.");

        final byte[] bytes = new byte[12];
        final String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decodeV1(ecode);
    }

    @Test
    public void decodeV1Test_illegalVersion() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal ecode version 16.");

        final byte[] bytes = new byte[13];
        bytes[0] |= 0xf0;

        final String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decodeV1(ecode);
    }

    @Test
    public void decodeV1Test_illegalLocale() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal locale ID 15.");

        final byte[] bytes = new byte[13];
        bytes[0] |= 0x0f;

        final String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decodeV1(ecode);
    }

    @Test
    public void decodeV1Test_illegalAlign() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal align ID 3.");

        final byte[] bytes = new byte[13];
        bytes[1] |= 0x03;

        final String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decodeV1(ecode);
    }

    @Test
    public void decodeV1Test_illegalSize() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal size ID 15.");

        final byte[] bytes = new byte[13];
        bytes[2] |= 0xf0;

        final String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decodeV1(ecode);
    }

    @Test
    public void decodeV1Test_illegalFormat() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal format ID 15.");

        final byte[] bytes = new byte[13];
        bytes[2] |= 0x0f;

        final String ecode = Base64.encodeBase64URLSafeString(bytes);
        ECODE_DECODER.decodeV1(ecode);
    }
}
