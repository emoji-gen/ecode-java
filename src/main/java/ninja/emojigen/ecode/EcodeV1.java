package ninja.emojigen.ecode;

import java.util.Collections;
import java.util.Set;

public class EcodeV1 extends Ecode {
    private final EcodeLocale locale;
    private final Set<EcodeFlag> flags;
    private final EcodeAlign textAlign;
    private final EcodeSize size;
    private final int fontId;
    private final int foregroundColor;
    private final int backgroundColor;
    private final String text;

    public EcodeV1(
        final EcodeLocale locale,
        final Set<EcodeFlag> flags,
        final EcodeAlign textAlign,
        final EcodeSize size,
        final int fontId,
        final int foregroundColor,
        final int backgroundColor,
        final String text
    ) {
        this.locale = locale;
        this.flags = Collections.unmodifiableSet(flags);
        this.textAlign = textAlign;
        this.size = size;
        this.fontId = fontId;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.text = text;
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

    public EcodeAlign getTextAlign() {
        return textAlign;
    }

    public EcodeSize getSize() {
        return size;
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
