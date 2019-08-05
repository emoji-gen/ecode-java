package ninja.emojigen.ecode;

public class Ecode {
    enum Locale {
        JP,
    }

    public final EcodeVersion version = EcodeVersion.VERSION_1;
    public final Locale locale = Locale.JP;

    public Ecode() {
    }
}
