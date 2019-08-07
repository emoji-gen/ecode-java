package ninja.emojigen.ecode;

import java.util.Set;

public class EcodeV1Builder {
    private EcodeLocale locale;
    private Set<EcodeFlag> flags;
    private EcodeTextAlign textAlign;
    private int fontId;

    public EcodeV1Builder locale(final EcodeLocale locale) {
        this.locale = locale;
        return this;
    }

    public EcodeV1Builder flags(final Set<EcodeFlag> flags) {
        this.flags = flags;
        return this;
    }

    public EcodeV1Builder textAlign(final EcodeTextAlign textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public EcodeV1Builder fontId(final int fontId) {
        this.fontId = fontId;
        return this;
    }

    public EcodeV1 build() {
        throw new RuntimeException("not implemented");
    }
}
