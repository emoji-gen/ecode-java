package ninja.emoji_gen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum EcodeSize {
    MDPI(0),
    HDPI(1),
    XHDPI(2),
    XXHDPI(3),
    ;

    EcodeSize(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    private static Map<Integer, EcodeSize> ID_TO_SIZE;

    static {
        ID_TO_SIZE = new HashMap<>();
        for (EcodeSize size : EcodeSize.values()) {
            ID_TO_SIZE.put(size.getId(), size);
        }
    }

    public static EcodeSize fromId(int id) {
        if (!ID_TO_SIZE.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Illegal size ID %d.", id));
        }
        return ID_TO_SIZE.get(id);
    }
}
