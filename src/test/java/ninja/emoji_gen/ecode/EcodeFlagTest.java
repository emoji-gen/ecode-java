package ninja.emoji_gen.ecode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EcodeFlagTest {
    @Test
    public void fromIdTest() {
        assertEquals(EcodeFlag.SIZE_FIXED, EcodeFlag.fromId(EcodeFlag.SIZE_FIXED.getId()));
        assertEquals(EcodeFlag.STRETCH, EcodeFlag.fromId(EcodeFlag.STRETCH.getId()));
    }
}
