package ninja.emojigen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum EcodeLocale {
    JA(0),
    KO(1),
    ZH_HANT(2),
    ZH_HANS(3),
    EN(4),
    ;

    EcodeLocale(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    private static Map<Integer, EcodeLocale> ID_TO_LOCALE;

    static {
        ID_TO_LOCALE = new HashMap<>();
        for (EcodeLocale locale : EcodeLocale.values()) {
            ID_TO_LOCALE.put(locale.getId(), locale);
        }
    }

    @Nullable
    public static EcodeLocale fromId(final int id) {
        return ID_TO_LOCALE.get(id);
    }
}
