package ninja.emoji_gen.ecode;

import java.util.HashMap;
import java.util.Locale;
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

    public String getCode() {
        return name().toLowerCase(Locale.US);
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

    /**
     * Returns {@code EcodeSize} object associated with {@code code}
     *
     * @param code Size code
     * @return {@code EcodeSize} object associated with {@code code}
     * @throws NullPointerException     if {@code code} is {@code null}.
     * @throws IllegalArgumentException if {@code code} isn't supported value.
     */
    public static EcodeSize fromCode(String code) {
        return valueOf(code.toUpperCase());
    }
}
