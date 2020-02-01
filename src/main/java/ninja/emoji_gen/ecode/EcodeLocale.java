package ninja.emoji_gen.ecode;

import java.util.HashMap;
import java.util.Map;

public enum EcodeLocale {
    JA(0),
    KO(1),
    ZH_HANT(2),
    ZH_HANS(3),
    EN(4),
    ;

    EcodeLocale(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    public String getCode() {
        switch (this) {
            case ZH_HANT:
                return "zh-Hant";
            case ZH_HANS:
                return "zh-Hans";
            default:
                return name().toLowerCase();
        }
    }

    private static Map<Integer, EcodeLocale> ID_TO_LOCALE;

    static {
        ID_TO_LOCALE = new HashMap<>();
        for (EcodeLocale locale : EcodeLocale.values()) {
            ID_TO_LOCALE.put(locale.getId(), locale);
        }
    }

    /**
     * Returns {@code EcodeLocale} object associated with {@code id}
     *
     * @param id Locale ID
     * @return {@code EcodeLocale} object associated with {@code id}
     * @throws IllegalArgumentException if {@code id} isn't supported value.
     */
    public static EcodeLocale fromId(int id) {
        if (!ID_TO_LOCALE.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Illegal locale ID %d.", id));
        }
        return ID_TO_LOCALE.get(id);
    }

    /**
     * Returns {@code EcodeLocale} object associated with {@code code}
     *
     * @param code Locale code
     * @return {@code EcodeLocale} object associated with {@code code}
     * @throws NullPointerException     if {@code code} is {@code null}.
     * @throws IllegalArgumentException if {@code code} isn't supported value.
     */
    public static EcodeLocale fromCode(String code) {
        String upperCode = code.toUpperCase();
        if ("ZH".equals(upperCode)) {
            return ZH_HANS;
        }
        return valueOf(upperCode.replace('-', '_'));
    }
}
