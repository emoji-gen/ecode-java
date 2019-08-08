package ninja.emojigen.ecode;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class EcodeEncoder {
    private static final int V1_HEADER_LENGTH = 12;
    private static final int V1_FLAG_LENGTH = 6;

    public String encode(EcodeV1 ecode) {
        final byte[] encodedText = ecode.getText().getBytes(StandardCharsets.UTF_8);
        final byte[] ecodeBytes = new byte[V1_HEADER_LENGTH + encodedText.length];

        ecodeBytes[0] |= ecode.getLocale().getId() & 0x0f;

        for (EcodeFlag flag : ecode.getFlags()) {
            if (flag.getId() >= V1_FLAG_LENGTH) {
                throw new IllegalStateException(
                    String.format("Illegal flag ID %d", flag.getId()));
            }
            ecodeBytes[1] |= 0x80 >>> flag.getId();
        }

        ecodeBytes[1] |= ecode.getTextAlign().getId() & 0x03;
        ecodeBytes[2] |= (ecode.getSize().getId() << 4) & 0xf0;
        // FORMAT: 4
        ecodeBytes[3] |= ecode.getFontId() & 0xff;
        ecodeBytes[4] |= ecode.getForegroundColor() >>> 24 & 0xff;
        ecodeBytes[5] |= ecode.getForegroundColor() >>> 16 & 0xff;
        ecodeBytes[6] |= ecode.getForegroundColor() >>> 8 & 0xff;
        ecodeBytes[7] |= ecode.getForegroundColor() & 0xff;
        ecodeBytes[8] |= ecode.getBackgroundColor() >>> 24 & 0xff;
        ecodeBytes[9] |= ecode.getBackgroundColor() >>> 16 & 0xff;
        ecodeBytes[10] |= ecode.getBackgroundColor() >>> 8 & 0xff;
        ecodeBytes[11] |= ecode.getBackgroundColor() & 0xff;

        System.arraycopy(encodedText, 0, ecodeBytes, V1_HEADER_LENGTH, encodedText.length);

        return Base64.encodeBase64URLSafeString(ecodeBytes);
    }
}
