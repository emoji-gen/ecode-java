package ninja.emojigen.ecode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EcodeAlignTest {
    @Test
    public void fromIdTest() {
        assertEquals(EcodeAlign.LEFT, EcodeAlign.fromId(EcodeAlign.LEFT.getId()));
        assertEquals(EcodeAlign.CENTER, EcodeAlign.fromId(EcodeAlign.CENTER.getId()));
        assertEquals(EcodeAlign.RIGHT, EcodeAlign.fromId(EcodeAlign.RIGHT.getId()));
    }
}
