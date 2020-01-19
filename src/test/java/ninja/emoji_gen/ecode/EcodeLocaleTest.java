package ninja.emoji_gen.ecode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EcodeLocaleTest {
    @Test
    public void getIdTest() {
        assertEquals(0, EcodeLocale.JA.getId());
        assertEquals(1, EcodeLocale.KO.getId());
        assertEquals(2, EcodeLocale.ZH_HANT.getId());
        assertEquals(3, EcodeLocale.ZH_HANS.getId());
        assertEquals(4, EcodeLocale.EN.getId());
    }

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

    @Test
    public void fromCodeTest_unknown() {
        assertNull(EcodeLocale.fromCode("unknown"));
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ConstantConditions")
    public void fromCodeTest_null() {
        EcodeLocale.fromCode(null);
    }
}
