package ninja.emojigen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum EcodeAlign {
    LEFT(0),
    CENTER(1),
    RIGHT(2),
    ;

    EcodeAlign(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    private static Map<Integer, EcodeAlign> ID_TO_ALIGN;

    static {
        ID_TO_ALIGN = new HashMap<>();
        for (EcodeAlign align : EcodeAlign.values()) {
            ID_TO_ALIGN.put(align.getId(), align);
        }
    }

    @Nullable
    public static EcodeAlign fromId(final int id) {
        return ID_TO_ALIGN.get(id);
    }
}
