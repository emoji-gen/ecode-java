package ninja.emoji_gen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum EcodeFormat {
    PNG(0),
    WEBP(1),
    ;

    EcodeFormat(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    private static Map<Integer, EcodeFormat> ID_TO_FORMAT;

    static {
        ID_TO_FORMAT = new HashMap<>();
        for (EcodeFormat flag : EcodeFormat.values()) {
            ID_TO_FORMAT.put(flag.getId(), flag);
        }
    }

    @Nullable
    public static EcodeFormat fromId(final int id) {
        return ID_TO_FORMAT.get(id);
    }
}
