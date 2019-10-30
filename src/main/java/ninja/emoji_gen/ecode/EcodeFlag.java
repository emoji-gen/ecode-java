package ninja.emoji_gen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.HashMap;
import java.util.Map;


public enum EcodeFlag {
    SIZE_FIXED(0),
    STRETCH(1),
    ;

    EcodeFlag(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    int getMask() {
        return 0b0000_0001 << id;
    }

    private static Map<Integer, EcodeFlag> ID_TO_FLAG;

    static {
        ID_TO_FLAG = new HashMap<>();
        for (EcodeFlag flag : EcodeFlag.values()) {
            ID_TO_FLAG.put(flag.getId(), flag);
        }
    }

    @Nullable
    public static EcodeFlag fromId(final int id) {
        return ID_TO_FLAG.get(id);
    }
}
