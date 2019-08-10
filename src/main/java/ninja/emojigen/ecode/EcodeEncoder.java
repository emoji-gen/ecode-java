package ninja.emojigen.ecode;

import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class EcodeEncoder {
    protected static final int V1_HEADER_LENGTH = 12;

    public String encodeV1(final EcodeV1 ecode) {
        final byte[] encodedText = ecode.getText().getBytes(StandardCharsets.UTF_8);
        if (encodedText.length == 0) {
            throw new IllegalStateException("empty string is not allowed");
        }

        final ByteBuffer byteBuffer = ByteBuffer.allocate(V1_HEADER_LENGTH + encodedText.length);
        byteBuffer.put((byte) (ecode.getLocale().getId() & 0x0f));
        byteBuffer.put((byte) (
            encodeFlagsV1(ecode.getFlags()) << 2 & 0b1111_1100 | ecode.getAlign().getId() & 0b0000_0011));
        byteBuffer.put((byte) (ecode.getSize().getId() << 4 & 0xf0 | ecode.getFormat().getId() & 0x0f));
        byteBuffer.put((byte) (ecode.getFontId() & 0xff));
        byteBuffer.putInt(ecode.getForegroundColor());
        byteBuffer.putInt(ecode.getBackgroundColor());
        byteBuffer.put(encodedText);

        // I think `java.util.Base64` is great,
        // but it not works on below Android 8.0 (API level 26) :(
        return Base64.encodeBase64URLSafeString(byteBuffer.array());
    }

    int encodeFlagsV1(final Set<EcodeFlag> flags) {
        int mask = 0x00;
        for (final EcodeFlag flag : flags) {
            mask |= flag.getMask();
        }

        return mask;
    }
}
