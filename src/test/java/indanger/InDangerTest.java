package indanger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InDangerTest {

    @Test
    public void toInt() {
        assertEquals(2, InDanger.toInt("02e0"));
        assertEquals(10, InDanger.toInt("10e0"));
        assertEquals(10, InDanger.toInt("01e1"));
        assertEquals(66000000, InDanger.toInt("66e6"));
    }

    @Test
    public void safePosition() {
        assertEquals(1, InDanger.safePosition(4));
        assertEquals(3, InDanger.safePosition(5));
        assertEquals(5, InDanger.safePosition(6));
        assertEquals(7, InDanger.safePosition(7));
        assertEquals(1, InDanger.safePosition(8));
        assertEquals(21, InDanger.safePosition(42));
        assertEquals(64891137, InDanger.safePosition(66000000));
    }
}
