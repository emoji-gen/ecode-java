package ninja.emojigen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class EcodeV1Builder {
    private EcodeLocale locale = EcodeLocale.JP;
    private Set<EcodeFlag> flags = EnumSet.noneOf(EcodeFlag.class);
    private EcodeAlign align = EcodeAlign.LEFT;
    private EcodeSize size = EcodeSize.MDPI;
    private EcodeFormat format = EcodeFormat.PNG;
    private int fontId;
    private int foregroundColor = 0x000000FF;
    private int backgroundColor = 0xFFFFFFFF;

    @Nullable
    private String text;

    public EcodeV1Builder locale(final EcodeLocale locale) {
        this.locale = Objects.requireNonNull(locale);
        return this;
    }

    public EcodeV1Builder flags(final Set<EcodeFlag> flags) {
        this.flags = Objects.requireNonNull(flags);
        return this;
    }

    public EcodeV1Builder align(final EcodeAlign align) {
        this.align = Objects.requireNonNull(align);
        return this;
    }

    public EcodeV1Builder size(final EcodeSize size) {
        this.size = Objects.requireNonNull(size);
        return this;
    }

    public EcodeV1Builder format(final EcodeFormat format) {
        this.format = Objects.requireNonNull(format);
        return this;
    }

    public EcodeV1Builder fontId(final int fontId) {
        this.fontId = fontId;
        return this;
    }

    public EcodeV1Builder foregroundColor(final int foregroundColor) {
        this.foregroundColor = foregroundColor;
        return this;
    }

    public EcodeV1Builder backgroundColor(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public EcodeV1Builder text(final String text) {
        if (text.isEmpty()) { // implicit NPE
            throw new IllegalArgumentException("empty string is not allowed");
        }

        this.text = text;
        return this;
    }

    public EcodeV1 build() {
        if (text == null) {
            throw new IllegalStateException("`text` is required");
        }

        return new EcodeV1(
            locale,
            flags,
            align,
            size,
            format,
            fontId,
            foregroundColor,
            backgroundColor,
            text
        );
    }
}
