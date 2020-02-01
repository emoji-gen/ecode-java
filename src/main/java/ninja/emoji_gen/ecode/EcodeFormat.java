package ninja.emoji_gen.ecode;

import java.util.HashMap;
import java.util.Map;

public enum EcodeFormat {
    PNG(0),
    WEBP(1),
    ;

    private static Map<Integer, EcodeFormat> ID_TO_FORMAT;

    static {
        ID_TO_FORMAT = new HashMap<>();
        for (EcodeFormat flag : EcodeFormat.values()) {
            ID_TO_FORMAT.put(flag.getId(), flag);
        }
    }

    /**
     * Returns {@code EcodeFormat} object associated with {@code id}
     *
     * @param id Format ID
     * @return {@code EcodeFormat} object associated with {@code id}
     * @throws IllegalArgumentException if {@code id} isn't supported value.
     */
    public static EcodeFormat fromId(final int id) {
        if (!ID_TO_FORMAT.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Illegal format ID %d.", id));
        }
        return ID_TO_FORMAT.get(id);
    }

    EcodeFormat(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }
}
