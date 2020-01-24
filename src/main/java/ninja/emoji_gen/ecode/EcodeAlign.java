package ninja.emoji_gen.ecode;

import java.util.HashMap;
import java.util.Map;

public enum EcodeAlign {
    LEFT(0),
    CENTER(1),
    RIGHT(2),
    ;

    EcodeAlign(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    public String getCode() {
        return name().toLowerCase();
    }

    private static Map<Integer, EcodeAlign> ID_TO_ALIGN;

    static {
        ID_TO_ALIGN = new HashMap<>();
        for (EcodeAlign align : EcodeAlign.values()) {
            ID_TO_ALIGN.put(align.getId(), align);
        }
    }

    /**
     * Returns {@code EcodeAlign} object associated with {@code id}
     *
     * @param id Align ID
     * @return {@code EcodeAlign} object associated with {@code id}
     * @throws IllegalArgumentException if {@code id} isn't supported value.
     */
    public static EcodeAlign fromId(int id) {
        if (!ID_TO_ALIGN.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Illegal align ID %d.", id));
        }
        return ID_TO_ALIGN.get(id);
    }
}
