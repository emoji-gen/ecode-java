package ninja.emoji_gen.ecode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EcodeAlignTest {
    @Test
    public void fromIdTest() {
        assertEquals(EcodeAlign.LEFT, EcodeAlign.fromId(EcodeAlign.LEFT.getId()));
        assertEquals(EcodeAlign.CENTER, EcodeAlign.fromId(EcodeAlign.CENTER.getId()));
        assertEquals(EcodeAlign.RIGHT, EcodeAlign.fromId(EcodeAlign.RIGHT.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromIdTest_illegalId() {
        EcodeAlign.fromId(10000);
    }

    @Test
    public void getIdTest() {
        assertEquals(0, EcodeAlign.LEFT.getId());
        assertEquals(1, EcodeAlign.CENTER.getId());
        assertEquals(2, EcodeAlign.RIGHT.getId());
    }

    @Test
    public void getCodeTest() {
        assertEquals("left", EcodeAlign.LEFT.getCode());
        assertEquals("center", EcodeAlign.CENTER.getCode());
        assertEquals("right", EcodeAlign.RIGHT.getCode());
    }
}
