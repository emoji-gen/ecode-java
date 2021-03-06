package ninja.emoji_gen.ecode;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.Set;

import static ninja.emoji_gen.ecode.EcodeEncoder.V1_HEADER_LENGTH;

public class EcodeDecoder {
    public Ecode decode(String ecode) {
        byte[] bytes = Base64.decodeBase64(ecode);
        if (bytes.length <= V1_HEADER_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Illegal byte length %d.", bytes.length));
        }

        validateVersion(bytes[0]);

        EcodeLocale locale = EcodeLocale.fromId(bytes[0] & 0x0f);
        Set<EcodeFlag> flags = parseFlags(bytes[1]);
        EcodeAlign align = EcodeAlign.fromId(bytes[1] & 0x03);
        EcodeSize size = EcodeSize.fromId(bytes[2] >>> 4 & 0x0f);
        EcodeFormat format = EcodeFormat.fromId(bytes[2] & 0x0f);

        int fontId = bytes[3] & 0xff;
        int foregroundColor =
            (bytes[4] & 0xff) << 24 | (bytes[5] & 0xff) << 16 | (bytes[6] & 0xff) << 8 | bytes[7] & 0xff;
        int backgroundColor =
            (bytes[8] & 0xff) << 24 | (bytes[9] & 0xff) << 16 | (bytes[10] & 0xff) << 8 | bytes[11] & 0xff;

        // `StandardCharsets` can be used Android 4.4 (API level 19) or later.
        String text = new String(bytes, V1_HEADER_LENGTH, bytes.length - V1_HEADER_LENGTH, StandardCharsets.UTF_8);

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

    void validateVersion(byte byte0) {
        int version = (byte0 >>> 4 & 0x0f) + 1;
        if (version != 1) {
            throw new IllegalArgumentException(
                String.format("Illegal ecode version %d.", version));
        }
    }

    Set<EcodeFlag> parseFlags(byte byte1) {
        Set<EcodeFlag> flags = EnumSet.noneOf(EcodeFlag.class);
        for (EcodeFlag flag : EcodeFlag.values()) {
            if (((byte1 >>> 2 & 0b0011_1111) & flag.getMask()) == flag.getMask()) {
                flags.add(flag);
            }
        }
        return flags;
    }
}
