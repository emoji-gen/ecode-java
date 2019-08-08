package ninja.emojigen.ecode;


import org.apache.commons.codec.binary.Base64;

import java.util.EnumSet;
import java.util.Set;

public class EcodeDecoder {
    private static final int V1_HEADER_LENGTH = 11;
    private static final int V1_FLAG_LENGTH = 6;

    public Ecode decode(String ecode) {
        final byte[] ecodeBytes = Base64.decodeBase64(ecode);
        final int version = ecodeBytes[0] >> 4 & 0x0f;

        switch (version) {
            case 0:
                return decodeV1(ecodeBytes);
            default:
                throw new IllegalArgumentException(
                    String.format("Ecode version %d is not supported.", version));
        }
    }

    protected EcodeV1 decodeV1(byte[] ecodeBytes) {
        if (ecodeBytes.length <= V1_HEADER_LENGTH) {
            throw new IllegalStateException(
                String.format("Illegal byte length %d.", ecodeBytes.length));
        }

        final int localeId = ecodeBytes[0] & 0x0f;
        final EcodeLocale locale = EcodeLocale.fromId(localeId);
        if (locale == null) {
            throw new IllegalStateException(
                String.format("Illegal locale ID %d.", localeId));
        }

        final Set<EcodeFlag> flags = EnumSet.noneOf(EcodeFlag.class);
        for (int i = 0; i < V1_FLAG_LENGTH; ++i) {
            if ((ecodeBytes[1] >>> 2 >>> i & 0x01) == 0x01) {
                final EcodeFlag flag = EcodeFlag.fromId(i);
                if (flag == null) {
                    throw new IllegalStateException(String.format("Illegal flag ID %d.", i));
                }
                flags.add(flag);
            }
        }

        final int alignId = ecodeBytes[1] & 0x03;
        final EcodeAlign textAlign = EcodeAlign.fromId(alignId);
        if (textAlign == null) {
            throw new IllegalStateException(
                String.format("Illegal text align ID %d.", alignId));
        }

        final int fontId = ecodeBytes[2] & 0xff;

        // XXX

        return new EcodeV1Builder()
            .locale(locale)
            .flags(flags)
            .textAlign(textAlign)
            .fontId(fontId)
            .build();
    }
}
