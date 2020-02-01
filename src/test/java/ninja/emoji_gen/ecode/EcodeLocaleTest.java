package ninja.emoji_gen.ecode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class EcodeLocaleTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getCodeTest() {
        assertEquals("ja", EcodeLocale.JA.getCode());
        assertEquals("ko", EcodeLocale.KO.getCode());
        assertEquals("zh-Hant", EcodeLocale.ZH_HANT.getCode());
        assertEquals("zh-Hans", EcodeLocale.ZH_HANS.getCode());
        assertEquals("en", EcodeLocale.EN.getCode());
    }

    @Test
    public void fromCodeTest_ja() {
        assertEquals(EcodeLocale.JA, EcodeLocale.fromCode("ja"));
        assertEquals(EcodeLocale.JA, EcodeLocale.fromCode("Ja"));
        assertEquals(EcodeLocale.JA, EcodeLocale.fromCode("JA"));
    }

    @Test
    public void fromCodeTest_ko() {
        assertEquals(EcodeLocale.KO, EcodeLocale.fromCode("ko"));
        assertEquals(EcodeLocale.KO, EcodeLocale.fromCode("Ko"));
        assertEquals(EcodeLocale.KO, EcodeLocale.fromCode("KO"));
    }

    @Test
    public void fromCodeTest_zh() {
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("zh"));
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("Zh"));
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("ZH"));
    }

    @Test
    public void fromCodeTest_zh_Hans() {
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("zh-hans"));
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("zh-Hans"));
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("zh_hans"));
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromCode("zh_Hans"));
    }

    @Test
    public void fromCodeTest_zh_Hant() {
        assertEquals(EcodeLocale.ZH_HANT, EcodeLocale.fromCode("zh-hant"));
        assertEquals(EcodeLocale.ZH_HANT, EcodeLocale.fromCode("zh-Hant"));
        assertEquals(EcodeLocale.ZH_HANT, EcodeLocale.fromCode("zh_hant"));
        assertEquals(EcodeLocale.ZH_HANT, EcodeLocale.fromCode("zh_Hant"));
    }

    @Test
    public void fromCodeTest_en() {
        assertEquals(EcodeLocale.EN, EcodeLocale.fromCode("en"));
        assertEquals(EcodeLocale.EN, EcodeLocale.fromCode("En"));
        assertEquals(EcodeLocale.EN, EcodeLocale.fromCode("EN"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromCodeTest_unknown() {
        EcodeLocale.fromCode("unknown");
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ConstantConditions")
    public void fromCodeTest_null() {
        EcodeLocale.fromCode(null);
    }

    @Test
    public void fromIdTest() {
        assertEquals(EcodeLocale.JA, EcodeLocale.fromId(0));
        assertEquals(EcodeLocale.KO, EcodeLocale.fromId(1));
        assertEquals(EcodeLocale.ZH_HANT, EcodeLocale.fromId(2));
        assertEquals(EcodeLocale.ZH_HANS, EcodeLocale.fromId(3));
        assertEquals(EcodeLocale.EN, EcodeLocale.fromId(4));
    }

    @Test
    public void fromIdTest_illegal() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal locale ID 100");

        EcodeLocale.fromId(100);
    }

    @Test
    public void getIdTest() {
        assertEquals(0, EcodeLocale.JA.getId());
        assertEquals(1, EcodeLocale.KO.getId());
        assertEquals(2, EcodeLocale.ZH_HANT.getId());
        assertEquals(3, EcodeLocale.ZH_HANS.getId());
        assertEquals(4, EcodeLocale.EN.getId());
    }
}
