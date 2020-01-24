package ninja.emoji_gen.ecode;

import moe.pine.nonnull.Nullable;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class EcodeBuilder {
    private EcodeLocale locale = EcodeLocale.JA;
    private Set<EcodeFlag> flags = EnumSet.noneOf(EcodeFlag.class);
    private EcodeAlign align = EcodeAlign.LEFT;
    private EcodeSize size = EcodeSize.MDPI;
    private EcodeFormat format = EcodeFormat.PNG;
    private int fontId;
    private int foregroundColor = 0x000000FF;
    private int backgroundColor = 0xFFFFFFFF;

    @Nullable
    private String text;

    public EcodeBuilder locale(final EcodeLocale locale) {
        this.locale = Objects.requireNonNull(locale);
        return this;
    }

    public EcodeBuilder flags(final Set<EcodeFlag> flags) {
        this.flags = Objects.requireNonNull(flags);
        return this;
    }

    public EcodeBuilder align(final EcodeAlign align) {
        this.align = Objects.requireNonNull(align);
        return this;
    }

    public EcodeBuilder size(final EcodeSize size) {
        this.size = Objects.requireNonNull(size);
        return this;
    }

    public EcodeBuilder format(final EcodeFormat format) {
        this.format = Objects.requireNonNull(format);
        return this;
    }

    public EcodeBuilder fontId(final int fontId) {
        this.fontId = fontId;
        return this;
    }

    public EcodeBuilder foregroundColor(final int foregroundColor) {
        this.foregroundColor = foregroundColor;
        return this;
    }

    public EcodeBuilder backgroundColor(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public EcodeBuilder text(final String text) {
        if (text.isEmpty()) { // implicit NPE
            throw new IllegalArgumentException("empty string is not allowed");
        }

        this.text = text;
        return this;
    }

    public Ecode build() {
        if (text == null) {
            throw new IllegalStateException("`text` is required");
        }

        return new Ecode(
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
