package ninja.emoji_gen.ecode;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.Set;

public class EcodeDecoder {
    @SuppressWarnings("WeakerAccess")
    protected static final int V1_HEADER_LENGTH = 12;

    public Ecode decode(String ecode) {
        final byte[] bytes = Base64.decodeBase64(ecode);
        if (bytes.length <= V1_HEADER_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Illegal byte length %d.", bytes.length));
        }

        validateV1Version(bytes[0]);

        final EcodeLocale locale = EcodeLocale.fromId(bytes[0] & 0x0f);

        final Set<EcodeFlag> flags = EnumSet.noneOf(EcodeFlag.class);
        for (final EcodeFlag flag : EcodeFlag.values()) {
            if (((bytes[1] >>> 2 & 0b0011_1111) & flag.getMask()) == flag.getMask()) {
                flags.add(flag);
            }
        }

        final EcodeAlign align = EcodeAlign.fromId(bytes[1] & 0x03);

        final int sizeId = bytes[2] >>> 4 & 0x0f;
        final EcodeSize size = EcodeSize.fromId(sizeId);
        if (size == null) {
            throw new IllegalArgumentException(
                String.format("Illegal size ID %d.", sizeId));
        }

        final int formatId = bytes[2] & 0x0f;
        final EcodeFormat format = EcodeFormat.fromId(formatId);
        if (format == null) {
            throw new IllegalArgumentException(
                String.format("Illegal format ID %d.", formatId));
        }

        final int fontId = bytes[3] & 0xff;
        final int foregroundColor =
            (bytes[4] & 0xff) << 24 | (bytes[5] & 0xff) << 16 | (bytes[6] & 0xff) << 8 | bytes[7] & 0xff;
        final int backgroundColor =
            (bytes[8] & 0xff) << 24 | (bytes[9] & 0xff) << 16 | (bytes[10] & 0xff) << 8 | bytes[11] & 0xff;

        // `StandardCharsets` can be used Android 4.4 (API level 19) or later.
        final String text = new String(bytes, V1_HEADER_LENGTH, bytes.length - V1_HEADER_LENGTH, StandardCharsets.UTF_8);

        return new EcodeBuilder()
            .locale(locale)
            .flags(flags)
            .align(align)
            .size(size)
            .format(format)
            .fontId(fontId)
            .foregroundColor(foregroundColor)
            .backgroundColor(backgroundColor)
            .text(text)
            .build();
    }

    void validateV1Version(byte byte0) {
        final int version = (byte0 >>> 4 & 0x0f) + 1;
        if (version != 1) {
            throw new IllegalArgumentException(
                String.format("Illegal ecode version %d.", version));
        }
    }
}
