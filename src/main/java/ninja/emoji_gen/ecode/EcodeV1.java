package ninja.emoji_gen.ecode;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class EcodeV1 extends Ecode {
    private final EcodeLocale locale;
    private final Set<EcodeFlag> flags;
    private final EcodeAlign align;
    private final EcodeSize size;
    private final EcodeFormat format;
    private final int fontId;
    private final int foregroundColor;
    private final int backgroundColor;
    private final String text;

    public EcodeV1(
        final EcodeLocale locale,
        final Set<EcodeFlag> flags,
        final EcodeAlign align,
        final EcodeSize size,
        final EcodeFormat format,
        final int fontId,
        final int foregroundColor,
        final int backgroundColor,
        final String text
    ) {
        this.locale = Objects.requireNonNull(locale);
        this.flags = Collections.unmodifiableSet(flags);
        this.align = Objects.requireNonNull(align);
        this.size = Objects.requireNonNull(size);
        this.format = Objects.requireNonNull(format);
        this.fontId = fontId;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.text = Objects.requireNonNull(text);
    }

    @Override
    public int getVersion() {
        return 1;
    }

    public EcodeLocale getLocale() {
        return locale;
    }

    public Set<EcodeFlag> getFlags() {
        return flags;
    }

    public EcodeAlign getAlign() {
        return align;
    }

    public EcodeSize getSize() {
        return size;
    }

    public EcodeFormat getFormat() {
        return format;
    }

    public int getFontId() {
        return fontId;
    }

    public int getForegroundColor() {
        return foregroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getText() {
        return text;
    }

}
