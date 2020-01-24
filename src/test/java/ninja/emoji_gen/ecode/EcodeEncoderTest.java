package ninja.emoji_gen.ecode;

import org.apache.commons.codec.binary.Base64;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.powermock.reflect.Whitebox;

import java.util.EnumSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class EcodeEncoderTest {
    private static final EcodeEncoder ECODE_ENCODER = new EcodeEncoder();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void encodeV1Test() {
        final Ecode ecode = new EcodeBuilder()
            .locale(EcodeLocale.EN)
            .flags(EnumSet.of(EcodeFlag.SIZE_FIXED, EcodeFlag.STRETCH))
            .align(EcodeAlign.CENTER)
            .size(EcodeSize.XHDPI)
            .format(EcodeFormat.WEBP)
            .fontId(0b1100_1111)
            .foregroundColor(0x12345678)
            .backgroundColor(0x9abcdef0)
            .text("ab\nc")
            .build();

        final String code = ECODE_ENCODER.encode(ecode);
        System.out.println("code=" + code); // "BA0hzxI0VniavN7wYWIKYw"

        final byte[] actual = Base64.decodeBase64(code);
        final byte[] expected =
            new byte[]{
                (byte) 0b0000_0100, // Version:4, Locale:4
                (byte) 0b0000_1101, // Flags:6, Align:2
                (byte) 0b0010_0001, // Size:4, Format:4
                (byte) 0b1100_1111, // FontId:8
                (byte) 0x12, // ForegroundColor_R:8
                (byte) 0x34, // ForegroundColor_G:8
                (byte) 0x56, // ForegroundColor_B:8
                (byte) 0x78, // ForegroundColor_A:8
                (byte) 0x9a, // BackgroundColor_R:8
                (byte) 0xbc, // BackgroundColor_G:8
                (byte) 0xde, // BackgroundColor_B:8
                (byte) 0xf0, // BackgroundColor_A:8
                (byte) 0x61, // Text:8
                (byte) 0x62, // Text:8
                (byte) 0x0a, // Text:8
                (byte) 0x63, // Text:8
            };

        assertArrayEquals(expected, actual);
    }

    @Test
    public void encodeV1Test_empty() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("empty string is not allowed");

        final Ecode ecode = new EcodeBuilder()
            .text("ab\nc")
            .build();
        Whitebox.setInternalState(ecode, "text", "");

        ECODE_ENCODER.encode(ecode);
    }

    @Test
    public void encodeFlagsV1() {
        assertEquals(0b0000_0000, ECODE_ENCODER.encodeFlagsV1(EnumSet.noneOf(EcodeFlag.class)));
        assertEquals(0b0000_0001, ECODE_ENCODER.encodeFlagsV1(EnumSet.of(EcodeFlag.SIZE_FIXED)));
        assertEquals(0b0000_0010, ECODE_ENCODER.encodeFlagsV1(EnumSet.of(EcodeFlag.STRETCH)));
        assertEquals(0b0000_0011, ECODE_ENCODER.encodeFlagsV1(EnumSet.of(EcodeFlag.SIZE_FIXED, EcodeFlag.STRETCH)));
    }
}
