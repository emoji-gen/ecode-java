package ninja.emojigen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum EcodeTextAlign {
    LEFT(0),
    CENTER(1),
    RIGHT(2),
    ;

    EcodeTextAlign(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    private static Map<Integer, EcodeTextAlign> ID_TO_TEXT_ALIGN;

    static {
        ID_TO_TEXT_ALIGN = new HashMap<>();
        for (EcodeTextAlign TextAlign : EcodeTextAlign.values()) {
            ID_TO_TEXT_ALIGN.put(TextAlign.getId(), TextAlign);
        }
    }

    @Nullable
    public static EcodeTextAlign fromId(final int id) {
        return ID_TO_TEXT_ALIGN.get(id);
    }
}
